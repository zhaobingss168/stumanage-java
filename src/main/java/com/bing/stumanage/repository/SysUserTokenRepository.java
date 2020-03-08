package com.bing.stumanage.repository;

import com.bing.stumanage.entity.SysUserToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * 系统用户token Repository
 * @author zhaobing
 * @date 2020/1/26
 */

@Repository
public interface SysUserTokenRepository extends JpaRepository<SysUserToken,Integer> {

    /**
     * 根据token查找token
     * @param token
     * @return
     */
    @Query(value="select * from sys_user_token where token = ?1",nativeQuery = true)
    SysUserToken findByToken(String token);

    /**
     * 根据userid查找user信息
     * @param userId  用户id
     * @return
     */
    @Query(value="select * from sys_user_token where user_id = ?1",nativeQuery = true)
    SysUserToken findByUserId(Integer userId);
}
