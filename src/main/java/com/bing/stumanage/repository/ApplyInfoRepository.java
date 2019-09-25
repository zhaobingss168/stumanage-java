package com.bing.stumanage.repository;

import com.bing.stumanage.entity.ApplyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ApplyInfoRepository extends JpaRepository<ApplyInfo,Integer> {

    @Query(value = "select * from apply_info where 1 = 1 " +
            " order by id asc limit ?1,?2 ",nativeQuery = true)
    List<ApplyInfo> queryByPage(Integer page, Integer size);

    @Query(value = "select count(1) from apply_info where 1 = 1 ",nativeQuery = true)
    int queryByPageTotalCount();
}
