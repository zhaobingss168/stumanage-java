package com.bing.stumanage.repository;

import com.bing.stumanage.entity.SysUserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public interface SysUserRoleRepository extends JpaRepository<SysUserRole,Integer> {

    /**
     * 根据用户user_id查找
     * @param userId
     * @return
     */
    @Query(value="select * from sys_user_role where user_id = ?1",nativeQuery = true)
    List<SysUserRole> querySysUserRoleByuserId(int userId);

    /**
     * 根据用户user_id删除
     * @param userId
     */
    @Modifying
    @Transactional
    @Query(value="delete from sys_user_role where user_id = ?1",nativeQuery = true)
    void deleteSysUserRoleByUserId(Integer userId);
}
