package com.md.service.model.entity;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户信息
 * </p>
 */
@Getter
@Setter
@ApiModel(value = "Users对象", description = "用户信息")
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户openId")
    private String openId;

    @ApiModelProperty("用户no")
    private String userNo;

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("用户头像")
    private String headUrl;

    @ApiModelProperty("用户性别 w 女 m 男 x 未知")
    private String sex;

    @ApiModelProperty("用户手机号")
    private String mobile;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;


    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @ApiModelProperty("用户状态")
    private Integer status;


    @ApiModelProperty("删除")
    @TableLogic
    private LocalDateTime deletedAt;

    private Integer role;


}
