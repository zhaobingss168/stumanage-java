package com.bing.stumanage.vo;

import com.sun.xml.internal.ws.developer.Serialization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.MappedSuperclass;

/**
 * 登录接口封装对象
 * @author zhaobing
 * @date 2020/1/26
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Serialization
@MappedSuperclass
@ApiModel(description = "登录参数实体")
public class LoginBean {

	@ApiModelProperty(value = "账号")
	private String username;// 账号

	@ApiModelProperty(value = "密码")
	private String password;// 密码

	@ApiModelProperty(value = "验证码")
	private String captcha;// 验证码
	
}
