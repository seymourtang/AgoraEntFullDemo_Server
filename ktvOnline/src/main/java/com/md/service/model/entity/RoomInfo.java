package com.md.service.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 房间信息
 * </p>
 */
@Getter
@Setter
@TableName("room_info")
@ApiModel(value = "RoomInfo对象", description = "房间信息")
public class RoomInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("房间名字")
    private String name;

    @ApiModelProperty("是否公开 0 公开 1加密")
    private Integer isPrivate;

    @ApiModelProperty("密码")
    private String password;

    @ApiModelProperty("创建用户")
    private Integer creator;

    @ApiModelProperty("房间唯一标识")
    private String roomNo;

    @ApiModelProperty("是否合唱 0 不合唱 1合唱")
    private Integer isChorus;

    @ApiModelProperty("背景")
    private String bgOption;

    @ApiModelProperty("音效")
    private String soundEffect;

    @ApiModelProperty("美声")
    private String belCanto;

    @ApiModelProperty("图标")
    private String icon;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @ApiModelProperty("房间状态 0 正常 1关闭 2异常")
    private Integer status;

    @TableLogic
    @ApiModelProperty("删除")
    private LocalDateTime deletedAt;


}
