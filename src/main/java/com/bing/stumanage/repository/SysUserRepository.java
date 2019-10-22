package com.bing.stumanage.repository;

import com.bing.stumanage.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysUserRepository extends JpaRepository<SysUser,Integer> {

    @Query(value="select * from sys_user where user_name = ?1 and status = 1",nativeQuery = true)
    SysUser findByUserName(String userName);

    @Query(value = "select * from sys_user where 1 = 1 " +
            "and (user_name like CONCAT('%',?1,'%') or ?1 is null) " +
            "and (nick_name like CONCAT('%',?2,'%') or ?2 is null ) " +
            " order by create_time DESC limit ?3,?4 ",nativeQuery = true)
    List<SysUser> queryByPage(String userName,String nickName,Integer page,Integer size);

    @Query(value = "select count(1) from sys_user where 1 = 1 "+
            "and (user_name like CONCAT('%',?1,'%') or ?1 is null) " +
            "and (nick_name like CONCAT('%',?2,'%') or ?2 is null ) " ,nativeQuery = true)
    int queryByPageTotalCount(String userName,String nickName);

    @Query(value="select * from sys_user where token= ?1 and role_id = ?2",nativeQuery = true)
    SysUser findTokenAndRoleId(String token, Integer integer);
}
