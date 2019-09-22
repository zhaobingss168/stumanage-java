package com.bing.stumanage.repository;

import com.bing.stumanage.entity.SessionValidCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * 根据sessionId查询库中存储的验证码
 **/

public interface SessionValidCodeRepository extends JpaRepository<SessionValidCode,Integer> {
    @Query(value = "select * from session_valid_code where session_id = ?1",nativeQuery = true)
    SessionValidCode findOneBySessionId(String sessionId);
}
