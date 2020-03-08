package com.bing.stumanage.entity;

import com.sun.xml.internal.ws.developer.Serialization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @Description 用户角色表
 * @Author zhaobing
 * @Date 2019/12/10 20:27
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Serialization
@ApiModel(description = "用户角色表")
public class SysUserRole extends BaseEntity{

    @ApiModelProperty(value = "用户id")
    private Integer userId;// 用户id

    @ApiModelProperty(value = "角色id")
    private Integer roleId;// 角色id

}
