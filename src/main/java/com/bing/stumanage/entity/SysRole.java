package com.bing.stumanage.entity;

import com.sun.xml.internal.ws.developer.Serialization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;

/**
 * @Description 角色表
 * @Author zhaobing
 * @Date 2019/12/10 20:23
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Serialization
@ApiModel(description = "角色表")
public class SysRole extends BaseEntity {

    @ApiModelProperty(value = "名称")
    private String name;// 名称

    @ApiModelProperty(value = "备注")
    private String remark; // 备注

}
