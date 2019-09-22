package com.bing.stumanage.repository;

import com.bing.stumanage.entity.SysConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * create by zhaobing
 * date 2019/4/11 下午7:34
 **/

public interface SysConfigRepository extends JpaRepository<SysConfig,Integer> {

    /**
     * 查询 系统配置 by code
     * @param code
     * @return
     */
    @Query(value="select * from sys_config sc where sc.config_code = ?1 and status = 1",nativeQuery = true)
    SysConfig findByConfigCode(String code);

}
