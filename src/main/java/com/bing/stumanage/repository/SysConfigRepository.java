package com.bing.stumanage.repository;

import com.bing.stumanage.entity.SysConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统配置Repository
 */
@Repository
public interface SysConfigRepository extends JpaRepository<SysConfig,Integer> {


    @Query(value = "select * from sys_config where 1 = 1 " +
            "and (config_name like CONCAT('%',?1,'%') or ?1 is null) " +
            "order by create_time asc limit ?2,?3 ",nativeQuery = true)
    List<SysConfig> querySysConfigByPage(String configName, int i, Integer size);

    @Query(value = "select count(1) from sys_config where 1 = 1 " +
            "and (config_name like CONCAT('%',?1,'%') or ?1 is null) "
            ,nativeQuery = true)
    int querySysConfigByPageTotalCount(String configName);

    @Query(value = "select * from sys_config where  config_code = ?1" ,nativeQuery = true)
    SysConfig findByConfigCode(String code);
}
