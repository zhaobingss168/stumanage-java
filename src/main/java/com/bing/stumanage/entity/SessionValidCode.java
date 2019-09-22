package com.bing.stumanage.entity;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * 图形验证码存储bean
 **/

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Serialization
public class SessionValidCode {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; //主键id
    private String sessionId; // sessionId
    private String validCode; // 验证码
}
