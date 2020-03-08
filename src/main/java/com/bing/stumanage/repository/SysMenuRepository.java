package com.bing.stumanage.repository;
import com.bing.stumanage.entity.SysMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SysMenuRepository extends JpaRepository<SysMenu,Integer> {

    @Query(value="select m.* from sys_menu m, sys_user u, sys_user_role ur, sys_role_menu rm " +
            "where u.username = ?1 and u.id = ur.user_id " +
            "and ur.role_id = rm.role_id and rm.menu_id = m.id",nativeQuery = true)
    List<SysMenu> findByUserName(String username);

    @Query(value="select m.* from sys_menu m, sys_role_menu rm " +
            "where rm.role_id = ?1 and m.id = rm.menu_id",nativeQuery = true)
    List<SysMenu> findRoleMenus(Integer roleId);
}
