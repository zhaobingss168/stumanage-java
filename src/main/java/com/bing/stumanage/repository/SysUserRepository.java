package com.bing.stumanage.repository;

import com.bing.stumanage.entity.SysUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface SysUserRepository extends JpaRepository<SysUser,Integer> {

    /**
     * 根据用户名查询系统用户信息，并且是当前有效的
     * @param userName 用户名
     * @return
     */
    @Query(value="select * from sys_user where username = ?1 and status = 1",nativeQuery = true)
    SysUser findByUserName(String userName);

    @Query(value = "select * from sys_user where 1 = 1 " +
            "and (username like CONCAT('%',?1,'%') or ?1 is null) " +
            "and (status = ?2 or ?2 is null ) " +
            " order by create_time DESC limit ?3,?4 ",nativeQuery = true)
    List<SysUser> queryByPage(String username, Integer status, Integer page, Integer size);

    @Query(value = "select count(1) from sys_user where 1 = 1 " +
            "and (username like CONCAT('%',?1,'%') or ?1 is null)" +
            "and (status = ?2 or ?2 is null ) ",nativeQuery = true)
    int queryByPageTotalCount(String userName, Integer status);

    @Query(value="select * from sys_user where id = ?1 and status = '1'",nativeQuery = true)
    SysUser getUserByUserId(Integer userId);

    /**
     * 根据用户名查询系统用户信息
     * @param username 用户名
     * @return
     */
    @Query(value="select * from sys_user where username = ?1",nativeQuery = true)
    SysUser findByUserNameNeglectStatus(String username);

    /**
     * 根据电话号码和主键id查询查询系统用户信息
     * @param phone 电话号码
     * @param id 主键id
     * @return
     */
    @Query(value="select * from sys_user where phone = ?1 and id <> ?2",nativeQuery = true)
    SysUser findByPhoneAndId(String phone, Integer id);

    /**
     * 根据电话号码查询查询系统用户信息
     * @param phone 电话号码
     * @return
     */
    @Query(value="select * from sys_user where phone = ?1",nativeQuery = true)
    SysUser findByPhone(String phone);
}
