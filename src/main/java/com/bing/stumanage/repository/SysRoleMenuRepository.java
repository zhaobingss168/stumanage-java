package com.bing.stumanage.repository;

import com.bing.stumanage.entity.SysRoleMenu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SysRoleMenuRepository extends JpaRepository<SysRoleMenu,Integer> {

    @Modifying
    @Query(value = "delete from sys_role_menu where role_id= ?1",nativeQuery = true)
    void deleteByRoleId(long roleId);
}
