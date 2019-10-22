package com.bing.stumanage.filter;

import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * create by zhaobing
 * date 2018/10/18 下午1:28
 * 解决跨域问题
 **/
@Component
public class CrosFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig){

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        String []  allowDomain= {"http://localhost:9528","http://localhost:8080"};//测试环境允许的网址
        Set<String> allowedOrigins= new HashSet(Arrays.asList(allowDomain));
        String originHeader = request.getHeader("Origin");
        if (allowedOrigins.contains(originHeader)) {
            response.setHeader("Access-Control-Allow-Origin", originHeader);//测试环境允许的网址
            response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE,PUT");//真实请求允许的方法
            response.setHeader("Access-Control-Max-Age", "3600");//预检请求的有效期，单位为秒。有效期内，不会重复发送预检请求
            response.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, key, id, role, token");//服务器允许使用的字段
            response.setHeader("Access-Control-Allow-Credentials", "true");//设置为true使跨域 sessionid保持一致，Access-Control-Allow-Origin就不能设为*了，必须为具体地址
        }
        filterChain.doFilter(req, res);
    }

    @Override
    public void destroy() {

    }
}
