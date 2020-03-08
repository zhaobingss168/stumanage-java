package com.bing.stumanage.entity;

import com.sun.xml.internal.ws.developer.Serialization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
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
@ApiModel(description = "系统配置表")
public class SysConfig extends BaseEntity{

    @ApiModelProperty(value = "配置编码")
    private String configCode; // 配置编码

    @ApiModelProperty(value = "配置值")
    private String configValue; // 配置值

    @ApiModelProperty(value = "配置名称")
    private String configName; // 配置名称

    @ApiModelProperty(value = "备注")
    private String remark; // 备注

    @ApiModelProperty(value = "状态 0失效  1正常")
    private Integer status; // 状态 0失效  1正常

}
