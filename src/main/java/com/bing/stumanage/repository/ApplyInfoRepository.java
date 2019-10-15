package com.bing.stumanage.repository;

import com.bing.stumanage.entity.ApplyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplyInfoRepository extends JpaRepository<ApplyInfo,Integer> {
    // CONCAT返回结果为连接参数产生的字符串。如有任何一个参数为NULL ，则返回值为 NULL。
    @Query(value = "select * from apply_info where 1 = 1 " +
            "and (stu_name like CONCAT('%',?1,'%') or ?1 is null) " +
            "and (pay_status = ?2 or ?2 is null ) " +
            " order by create_time DESC limit ?3,?4 ",nativeQuery = true)
    List<ApplyInfo> queryByPage(String stuName,Integer payStatus,Integer page, Integer size);

    @Query(value = "select count(1) from apply_info where 1 = 1 " +
            "and (stu_name like CONCAT('%',?1,'%') or ?1 is null) " +
            "and (pay_status = ?2 or ?2 is null ) " ,nativeQuery = true)
    int queryByPageTotalCount(String stuName,Integer payStatus);

    @Query(value = "select * from apply_info where 1 = 1 " +
            "and (stu_name like CONCAT('%',?1,'%') or ?1 is null) " +
            "and (pay_status = ?2 or ?2 is null ) " +
            " order by create_time DESC",nativeQuery = true)
    List<ApplyInfo> queryAll(String stuName, Integer payStatus);
}
