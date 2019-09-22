package com.bing.stumanage.repository;

import com.bing.stumanage.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysUserRepository extends JpaRepository<SysUser,Integer> {
    @Query(value = "select * from sys_user where 1 = 1 " +
            "and (account like CONCAT('%',?1,'%') or ?1 is null) " +
            "and (unit_id = ?2 or ?2 is null ) " +
            "and (role_id = ?3 or ?3 is null ) " +
            "and (area_id = ?4 or ?4 is null ) " +
            "order by id asc limit ?5,?6 ",nativeQuery = true)
    List<SysUser> querySysUserByPage(String account, Integer unitId, Integer roleId, Integer areaId, Integer page, Integer size);

    @Query(value = "select count(1) from sys_user where 1 = 1 " +
            "and (account like CONCAT('%',?1,'%') or ?1 is null) " +
            "and (unit_id = ?2 or ?2 is null ) " +
            "and (role_id = ?3 or ?3 is null ) " +
            "and (area_id = ?4 or ?4 is null ) ",nativeQuery = true)
    int querySysUserByPageTotalCount(String account, Integer unitId, Integer roleId, Integer areaId);

    @Query(value="select * from sys_user where id=?1 and token=?2 and role_id = ?3",nativeQuery = true)
    SysUser findByIdAndTokenAndRoleId(int id, String token, int role_id);

    @Query(value="select * from sys_user where account = ?1 and status = 1",nativeQuery = true)
    SysUser findByAccount(String account);
}
