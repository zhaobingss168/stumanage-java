package com.bing.stumanage.interceptor;

import com.bing.stumanage.entity.SysUser;
import com.bing.stumanage.repository.SysUserRepository;
import com.bing.stumanage.uitls.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @Description 拦截器
 * @Author zhaobing
 * @Date 2019/9/16 20:40
 **/
@Component
public class LoginInterception implements HandlerInterceptor {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String role = request.getHeader("role"); //1：管理员 2：c端用户
        String token = request.getHeader("token"); // 在请求头中取出token
        // 如果某一参数为空，返回错误信息1001，缺少登录必要参数
        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(role)) {
            return getErrorResponse(response, Constant.PARAM_LACK);
        } else {
            // 根据token和角色id查询用户
            SysUser sysUser = sysUserRepository.findTokenAndRoleId(token, Integer.valueOf(role));
            // 如果没有查询出信息，返回1002，参数不少，但是值错误，即根据参数查询不到登录信息
            if (null == sysUser) {
                return getErrorResponse(response, Constant.PARAM_ERROR);
            } else {
                //登录超过30分钟，需重新登录
                if (ifTimeOut(sysUser.getLoginTime())) {
                    return getErrorResponse(response, Constant.TIME_OUT);
                } else {
                    //更新登录时间
                    sysUser.setLoginTime(new Date());
                    sysUserRepository.save(sysUser);
                    return true;
                }
            }
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }

    /**
     * 抽取公共错误返回
     *
     * @param response
     * @param value
     * @return
     */
    private boolean getErrorResponse(HttpServletResponse response, String value) throws IOException {
        response.setContentType("text/html;charset=utf-8");
        try {
            response.getWriter().write(value);
        } catch (IOException e) {
            response.getWriter().write(value);
            e.printStackTrace();
        }
        return false;
    }

    /**
     * 抽取时间比较，判断登录是否超时
     * @param loginTime
     * @return
     */
    private boolean ifTimeOut(Date loginTime) {
        Date date = new Date();
        long ms = date.getTime() - loginTime.getTime();
        long min = ms / (1000 * 60);
        if (min > Constant.TIME_OUT_VALUE) {
            return true;
        } else {
            return false;
        }
    }
}
