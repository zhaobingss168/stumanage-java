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
 * create by zhaobing
 * date 2019/9/6 下午10:06
 * 系统配置
 **/

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Serialization
public class SysConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // 主键id
    private String configCode; // 配置编码
    private String configValue; // 配置值
    private String configName; // 配置名称
    private String remark; // 备注
    private int status; // 状态
    private Date createTime; // 创建时间
}
