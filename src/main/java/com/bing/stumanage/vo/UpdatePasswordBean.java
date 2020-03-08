package com.bing.stumanage.vo;

import com.sun.xml.internal.ws.developer.Serialization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

/**
 * @Description 修改密码bean
 * @Author zhaobing
 * @Date 2020/3/8 15:43
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor
@Serialization
@MappedSuperclass
@ApiModel(description = "修改密码参数实体")
public class UpdatePasswordBean {

    @ApiModelProperty(value = "用户名")
    private String username;// 用户名

    @ApiModelProperty(value = "原始密码")
    private String oldPwd;// 原始密码

    @ApiModelProperty(value = "新密码")
    private String newPwd;// 新密码

}
