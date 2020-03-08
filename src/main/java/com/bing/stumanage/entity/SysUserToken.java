package com.bing.stumanage.entity;

import com.sun.xml.internal.ws.developer.Serialization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Serialization
@ApiModel(description = "系统用户表")
public class SysUserToken extends BaseEntity {

    @ApiModelProperty(value = "用户id")
    private Integer userId;// 用户id

    @ApiModelProperty(value = "token")
    private String token;// token

    @ApiModelProperty(value = "到期时间")
    private Date expireTime;// 到期时间

}