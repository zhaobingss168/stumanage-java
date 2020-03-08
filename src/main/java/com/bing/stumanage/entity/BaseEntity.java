package com.bing.stumanage.entity;

import com.sun.xml.internal.ws.developer.Serialization;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;

/**
 * @Description 基础模型
 * @Author zhaobing
 * @Date 2019/1/24 20:01
 **/

/**
 * @Data:注解在类上；提供类所有属性的getting和setting方法,此外还提供了equals、canEqual、hashCode、toString方法
 * @EntityListeners(AuditingEntityListener.class):声明实体监听器,用于实体修改时做处理
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Serialization
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "主键id")
    private Integer id;// 主键id自增

    /**
     * 表示该字段为创建人，在这个实体被insert的时候，会自动为其赋值
     */
    @CreatedBy
    @Column(name = "create_by")
    @ApiModelProperty(value = "创建人")
    private String createBy;// 创建人

    /**
     * 表示该字段为创建时间字段，在这个实体被insert的时候，会自动为其赋值
     */
    @CreatedDate
    @Column(name = "create_time")
    @ApiModelProperty(value = "创建时间")
    private Date createTime;// 创建时间

    /**
     * 表示该字段为修改人，在这个实体被update的时候，会自动为其赋值
     */
    @LastModifiedBy
    @Column(name = "last_update_by")
    @ApiModelProperty(value = "最后操作人")
    private String lastUpdateBy;// 最后操作人

    /**
     * 表示该字段为修改时间字段，在这个实体被update的时候，会自动为其赋值
     */
    @LastModifiedDate
    @Column(name = "last_update_time")
    @ApiModelProperty(value = "最后操作时间")
    private Date lastUpdateTime;// 最后操作时间

}
