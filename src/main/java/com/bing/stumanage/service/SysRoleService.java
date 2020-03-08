package com.bing.stumanage.service;
import com.bing.stumanage.entity.SysMenu;
import com.bing.stumanage.entity.SysRole;
import com.bing.stumanage.entity.SysRoleMenu;
import com.bing.stumanage.utils.PageUtil;

import java.util.List;

public interface SysRoleService {

    PageUtil queryByPage(String roleName, Integer page, Integer size);

    List<SysRole> findAllRoles();

    SysRole save(SysRole sysRole);

    SysRole update(SysRole sysRole);

    void delete(List<SysRole> records);

    List<SysMenu> findRoleMenus(Integer roleId);

    void saveRoleMenus(List<SysRoleMenu> records);
}
