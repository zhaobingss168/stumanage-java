package com.bing.stumanage.repository;

import com.bing.stumanage.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SysUserRepository extends JpaRepository<SysUser,Integer> {

    @Query(value="select * from sys_user where user_name = ?1 and status = 1",nativeQuery = true)
    SysUser findByUserName(String userName);

    @Query(value = "select * from sys_user where 1 = 1 order by id asc limit ?1,?2 ",nativeQuery = true)
    List<SysUser> queryByPage(Integer page,Integer size);

    @Query(value = "select count(1) from sys_user where 1 = 1 ",nativeQuery = true)
    int queryByPageTotalCount();
}
