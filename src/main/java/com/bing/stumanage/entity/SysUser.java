package com.bing.stumanage.entity;

import com.sun.xml.internal.ws.developer.Serialization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户表
 * 2019-09-23 15:37:10
 * zhaobing
*/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Serialization
@ApiModel(description = "系统用户表")
public class SysUser extends BaseEntity{

    @ApiModelProperty(value = "用户名")
    private String username;// 用户名

    @ApiModelProperty(value = "密码")
    private String password;// 密码

    @ApiModelProperty(value = "电话")
    private String phone;// 电话

    @ApiModelProperty(value = "盐值")
    private String salt; // 盐值

    @ApiModelProperty(value = "昵称")
    private String nickName; // 昵称

    @ApiModelProperty(value = "状态  0冻结  1正常")
    private Integer status; // 状态  0冻结  1正常

    @ApiModelProperty(value = "最后登录时间")
    private Date lastLoginTime; // 最后登录时间

    @Transient
    private String token;
    @Transient
    private String roleNames;
    @Transient
    private List<SysUserRole> userRoles = new ArrayList<>();
}