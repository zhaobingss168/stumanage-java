package com.bing.stumanage.service;

import com.bing.stumanage.entity.SysUser;
import com.bing.stumanage.uitls.PageUtil;

public interface SysUserService {
    PageUtil queryByPage(String userName,String nickName,Integer page, Integer size);
    SysUser save(SysUser sysUser);
    SysUser update(SysUser sysUser);
    void delete(Integer id);
    SysUser findByUserName(String account);
}
