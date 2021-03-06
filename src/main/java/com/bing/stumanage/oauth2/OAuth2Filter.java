package com.bing.stumanage.oauth2;

import com.alibaba.fastjson.JSONObject;
import com.bing.stumanage.utils.Constant;
import com.bing.stumanage.utils.ResponseMessage;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Description Oauth2过滤器
 * @Author zhaobing
 * @Date 2019/1/24 20:01
 **/
public class OAuth2Filter extends AuthenticatingFilter {

    @Override
    protected AuthenticationToken createToken(ServletRequest request, ServletResponse response) throws Exception {
        // 获取请求token
        String token = getRequestToken((HttpServletRequest) request);
        if(StringUtils.isBlank(token)){
            return null;
        }
        return new OAuth2Token(token);
    }

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
    	return false;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
    	HttpServletRequest httpRequest = (HttpServletRequest) request;
    	if("OPTIONS".equals(httpRequest.getMethod())) {
    		// 如果是跨域中复杂请求的预检请求（OPTIONS类型），因为预检请求不带token, 所以不需要验证token
    		 return true;
    	}
        String typeId = httpRequest.getHeader("typeid");
    	if (StringUtils.isBlank(typeId)){
            HttpServletResponse httpResponse = (HttpServletResponse) response;
            return getErrorResponse(httpResponse, Constant.PARAM_LACK);
        }else if ("2".equals(typeId)){
    	    return true;
        }else if ("1".equals(typeId)){
            // 获取请求token，如果token不存在，直接返回401
            String token = getRequestToken(httpRequest);
            if(StringUtils.isBlank(token)){
                HttpServletResponse httpResponse = (HttpServletResponse) response;
                return getErrorResponse(httpResponse, Constant.PARAM_LACK);
            }
            return executeLogin(request, response);
        }
        return false;
    }

    private boolean getErrorResponse(HttpServletResponse response, String value) throws IOException {
        response.setContentType("application/json; charset=UTF-8");
        try {
            response.getWriter().write(value);
        } catch (IOException e) {
            response.getWriter().write(value);
            e.printStackTrace();
        }
        return false;
    }

    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        httpResponse.setContentType("application/json; charset=utf-8");
        try {
            // 处理登录失败的异常
            Throwable throwable = e.getCause() == null ? e : e.getCause();
            ResponseMessage result = ResponseMessage.errorWithMsg(throwable.getMessage().toString());
            String json = JSONObject.toJSONString(result);
            httpResponse.getWriter().print(json);
        } catch (IOException e1) {
        }
        return false;
    }

    /**
     * 获取请求的token
     */
    private String getRequestToken(HttpServletRequest httpRequest){
        // 从header中获取token
        String token = httpRequest.getHeader("token");
        // 如果header中不存在token，则从参数中获取token
        if(StringUtils.isBlank(token)){
            token = httpRequest.getParameter("token");
        }
        return token;
    }

}
