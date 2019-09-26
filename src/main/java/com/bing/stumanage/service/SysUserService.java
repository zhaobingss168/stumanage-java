package com.bing.stumanage.service;

import com.bing.stumanage.entity.SysUser;
import com.bing.stumanage.uitls.PageUtil;

public interface SysUserService {
    PageUtil queryByPage(Integer page, Integer size);
    SysUser save(SysUser sysUser);
    SysUser update(SysUser sysUser);
    void delete(Integer id);
    SysUser querySysUserById(Integer id);
    SysUser findByUserName(String account);
    void logout(String account);
    boolean changePassword(String account, String userPassword);
}
