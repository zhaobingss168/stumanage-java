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
 * @Description 报名信息
 * @Author zhaobing
 * @Date 2019/9/24 21:42
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Serialization
public class ApplyInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;// 主键id
    private String stuName;// 学生姓名
    private String stuPhone;// 学生电话
    private String readingSchool;// 在读学校
    private String readingMajor;//  在读专业
    private String applySchool;//  报名学校
    private String applyMajor;//  报名专业
    private int payStatus;// 支付状态 0未支付，1已支付
    private Date createTime;// 申请时间，创建时间
}
