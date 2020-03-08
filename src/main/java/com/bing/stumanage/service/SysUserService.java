package com.bing.stumanage.service;

import com.bing.stumanage.entity.SysUser;
import com.bing.stumanage.utils.PageUtil;
import com.bing.stumanage.utils.ResponseMessage;
import com.bing.stumanage.vo.UpdatePasswordBean;

import java.util.List;
import java.util.Set;

public interface SysUserService {
    PageUtil queryByPage(String username, Integer status, Integer page, Integer size);

    ResponseMessage save(SysUser sysUser);

    ResponseMessage update(SysUser sysUser);

    void delete(List<SysUser> records);

    SysUser findByUserName(String account);

    Set<String> findPermissionsByUserName(String userName);

    SysUser findByUserId(Integer userId);

    void commonSave(SysUser user);

    ResponseMessage updatePassword(UpdatePasswordBean updatePasswordBean);
}
