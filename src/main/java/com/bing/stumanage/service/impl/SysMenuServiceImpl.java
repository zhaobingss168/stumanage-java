package com.bing.stumanage.service.impl;


import com.bing.stumanage.constants.SysConstants;
import com.bing.stumanage.entity.SysMenu;
import com.bing.stumanage.repository.SysMenuRepository;
import com.bing.stumanage.service.SysMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.bing.stumanage.utils.CopyProperties;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @Description 菜单service
 * @Author zhaobing
 * @Date 2019/12/11 21:35
 **/
@Service
@Transactional
public class SysMenuServiceImpl implements SysMenuService {

    @Autowired
    private SysMenuRepository sysMenuRepository;// 菜单Repository


    /**
     * 根据用户名查询拥有的菜单
     * @param userName
     * @return
     */
    @Override
    public List<SysMenu> findByUserName(String userName) {
        // 如果是超级管理员，查询所有
        if (userName == null || "".equals(userName) || userName.equalsIgnoreCase(SysConstants.ADMIN)){
            return sysMenuRepository.findAll();
        }
        return sysMenuRepository.findByUserName(userName);
    }

    /**
     * 根据用户名查询菜单左侧导航树
     * @param userName 用户名
     * @param menuType 目录类型 0目录 1菜单 2按钮
     * @return
     */
    @Override
    public List<SysMenu> findNavTreeByUserName(String userName, int menuType) {
        List<SysMenu> sysMenus = new ArrayList<>();
        // 根据用户名查询出对应的菜单
        List<SysMenu> menus = findByUserName(userName);
        for (SysMenu menu : menus) {
            // 如果菜单没有父级或者父级id是0，将Level设置为0
            if (menu.getParentId() == null || menu.getParentId() == 0) {
                menu.setLevel(0);
                sysMenus.add(menu);
            }
        }
        // 用Lambda 表达式排序
        sysMenus.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
        // 查找子节点
        findChildren(sysMenus, menus, menuType);
        return sysMenus;
    }

    /**
     * 新增或修改菜单
     * @param sysMenu
     * @return
     */
    @Override
    public SysMenu save(SysMenu sysMenu) {
        Optional<SysMenu> optional = sysMenuRepository.findById(sysMenu.getId());
        if(optional.isPresent()){ // 修改
            SysMenu result = optional.get();
            CopyProperties.copyPropertiesIgnoreNull(sysMenu,result);
            if(result.getParentId() == null) {
                result.setParentId(0);
            }
            return sysMenuRepository.save(result);// 保存
        }else{// 新增
            if(sysMenu.getParentId() == null) {
                sysMenu.setParentId(0);
            }
            return sysMenuRepository.save(sysMenu);// 保存
        }
    }

    /**
     * 删除，支持批量删除
     * @param sysMenuList
     * @return
     */
    @Override
    public void delete(List<SysMenu> sysMenuList) {
        for (SysMenu sysMenu : sysMenuList) {
            sysMenuRepository.deleteById(sysMenu.getId());// 删除
        }
    }

    /**
     * 查找子节点
     * @param SysMenus
     * @param menus
     * @param menuType 目录类型 0目录 1菜单 2按钮
     */
    private void findChildren(List<SysMenu> SysMenus, List<SysMenu> menus, int menuType) {
        for (SysMenu SysMenu : SysMenus) {
            List<SysMenu> children = new ArrayList<>();
            for (SysMenu menu : menus) {
                if(menuType == 1 && menu.getType() == 2) {
                    // 如果是获取类型不需要按钮，且菜单类型是按钮的，直接过滤掉
                    continue ;
                }
                if (SysMenu.getId() != null && SysMenu.getId().equals(menu.getParentId())) {
                    menu.setParentName(SysMenu.getName());
                    menu.setLevel(SysMenu.getLevel() + 1);
                    children.add(menu);
                }
            }
            SysMenu.setChildren(children);
            children.sort((o1, o2) -> o1.getOrderNum().compareTo(o2.getOrderNum()));
            findChildren(children, menus, menuType);
        }
    }
}
