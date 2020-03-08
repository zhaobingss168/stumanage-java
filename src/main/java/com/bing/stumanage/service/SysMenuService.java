package com.bing.stumanage.service;



import com.bing.stumanage.entity.SysMenu;

import java.util.List;

public interface SysMenuService {

    List<SysMenu> findByUserName(String usreName);

    List<SysMenu> findNavTreeByUserName(String userName, int menuType);

    SysMenu save(SysMenu sysMenu);

    void delete(List<SysMenu> sysMenuList);
}
