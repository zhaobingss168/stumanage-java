package com.bing.stumanage.entity;

import com.sun.xml.internal.ws.developer.Serialization;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
/**
 * @Description 报名信息
 * @Author zhaobing
 * @Date 2019/9/24 21:42
 **/
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Serialization
@ApiModel(description = "报名信息表")
public class AppApplyInfo extends BaseEntity{

    @ApiModelProperty(value = "学生姓名")
    private String stuName;// 学生姓名

    @ApiModelProperty(value = "学生电话")
    private String stuPhone;// 学生电话

    @ApiModelProperty(value = "在读学校")
    private String readingSchool;// 在读学校

    @ApiModelProperty(value = "在读专业")
    private String readingMajor;//  在读专业

    @ApiModelProperty(value = "报名学校")
    private String applySchool;//  报名学校

    @ApiModelProperty(value = "报名专业")
    private String applyMajor;//  报名专业

    @ApiModelProperty(value = "付款状态 0未支付，1已支付")
    private Integer payStatus;// 付款状态 0未支付，1已支付

}
