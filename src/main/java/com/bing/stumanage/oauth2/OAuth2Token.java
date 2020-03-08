package com.bing.stumanage.oauth2;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Description 自定义 token 对象
 * @Author zhaobing
 * @Date 2019/1/24 20:01
 **/
public class OAuth2Token implements AuthenticationToken {
	private static final long serialVersionUID = 1L;
	
	private String token;

    public OAuth2Token(String token){
        this.token = token;
    }

    @Override
    public String getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
