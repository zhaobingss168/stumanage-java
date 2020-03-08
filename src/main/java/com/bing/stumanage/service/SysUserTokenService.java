package com.bing.stumanage.service;


import com.bing.stumanage.entity.SysUserToken;


public interface SysUserTokenService {

	SysUserToken findByToken(String token);

    SysUserToken createToken(Integer id);
}
