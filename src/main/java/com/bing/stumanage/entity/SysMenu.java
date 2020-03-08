package com.bing.stumanage.entity;

import com.sun.xml.internal.ws.developer.Serialization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Transient;
import java.util.List;

/**
 * @Description 用户菜单
 * @Author zhaobing
 * @Date 2019/12/10 20:33
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Serialization
@ApiModel(description = "用户菜单表")
public class SysMenu extends BaseEntity{

    @ApiModelProperty(value = "父级id")
    private Integer parentId;// 父级id

    @ApiModelProperty(value = "名称")
    private String name;// 名称

    @ApiModelProperty(value = "链接地址")
    private String url;// 链接地址

    @ApiModelProperty(value = "权限")
    private String perms;// 权限

    @ApiModelProperty(value = "类型")
    private Integer type;// 类型

    @ApiModelProperty(value = "图标")
    private String icon;// 图标

    @ApiModelProperty(value = "位置")
    private Integer orderNum;// 位置

    @Transient
    private String parentName; // 非数据库字段
    @Transient
    private Integer level; // 非数据库字段
    @Transient
    private List<SysMenu> children; // 非数据库字段
}
