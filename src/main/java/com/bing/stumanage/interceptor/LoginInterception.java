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
//        String token = request.getHeader("token");
//        String id = request.getHeader("id");
//        String role = request.getHeader("role"); //1：管理员 2：c端用户
//        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(id) || StringUtils.isEmpty(role)) {
//            return getErrorResponse(response, Constant.PARAM_LACK);
//        } else {
//            SysUser account = sysUserRepository.findByIdAndTokenAndRoleId(Integer.valueOf(id), token, Integer.valueOf(role));
//            if (account == null) {
//                return getErrorResponse(response, Constant.PARAM_ERROR);
//            } else {
//                if (ifTimeOut(account.getLoginTime())) { //登录超过30分钟，需重新登录
//                    return getErrorResponse(response, Constant.TIME_OUT);
//                } else {
//                    //更新登录时间
//                    account.setLoginTime(new Date());
//                    sysUserRepository.save(account);
//                    return true;
//                }
//            }
//        }
        return true;
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
     *
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
