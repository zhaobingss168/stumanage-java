package com.bing.stumanage.entity;

import com.sun.xml.internal.ws.developer.Serialization;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * 2019-07-29 15:37:10
 * zhaobing
*/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Serialization
public class SysUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;// 主键id
    private String userName;// 用户名
    private String password;// 密码
    private int roleId;// 角色id
    private String token; // token
    private String salt; // 盐
    private String random; //随机数
    private String nickName; // 昵称
    private int status; // 状态  0冻结  1正常
    private Date loginTime; // 上次登录时间
    private Date createTime; // 创建时间
}