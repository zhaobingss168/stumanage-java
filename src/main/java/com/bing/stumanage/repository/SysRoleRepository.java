package com.bing.stumanage.repository;
import com.bing.stumanage.entity.SysRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色Repository
 */
@Repository
public interface SysRoleRepository extends JpaRepository<SysRole,Integer> {

    @Query(value="select * from sys_role where id = ?1",nativeQuery = true)
    SysRole queryOneSysRoleByRoleId(Integer roleId);

    @Query(value = "select * from sys_role where 1 = 1 " +
            "and (name like CONCAT('%',?1,'%') or ?1 is null) " +
            "order by create_time DESC limit ?2,?3 ",nativeQuery = true)
    List<SysRole> queryByPage(String roleName, Integer i, Integer size);

    @Query(value = "select count(1) from sys_role where 1 = 1 " +
            "and (name like CONCAT('%',?1,'%') or ?1 is null) " ,nativeQuery = true)
    int queryByPageTotalCount(String roleName);
}
