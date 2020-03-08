package com.bing.stumanage.entity;

import com.sun.xml.internal.ws.developer.Serialization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @Description 权限菜单
 * @Author zhaobing
 * @Date 2019/12/10 20:38
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Serialization
@ApiModel(description = "角色菜单表")
public class SysRoleMenu extends BaseEntity{

    @ApiModelProperty(value = "角色id")
    private Integer roleId;// 角色id

    @ApiModelProperty(value = "菜单id")
    private Integer menuId;// 菜单id

}
