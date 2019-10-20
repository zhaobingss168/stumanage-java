package com.bing.stumanage.repository;

import com.bing.stumanage.entity.ApplyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface HomePageStatisticsRepository extends JpaRepository<ApplyInfo,Integer> {

    @Query(value = "SELECT " +
            "count( ai.id) totalPeople," +
            "count( CASE WHEN ai.pay_status = 0 THEN 1 ELSE NULL END ) pay0," +
            "count( CASE WHEN ai.pay_status = 1 THEN 1 ELSE NULL END ) pay1 " +
            "FROM apply_info ai",nativeQuery = true)
    List<Object> queryCount();

    @Query(value = "select count(1) total ,substr(create_time,1,10) time from apply_info where DATE_SUB(CURDATE(), INTERVAL 7 DAY) <= STR_TO_DATE(create_time, '%Y-%m-%d %H:%i:%s') group by time order by time asc ", nativeQuery = true)
    List<Object> getSevenDayUserTotal();
}
