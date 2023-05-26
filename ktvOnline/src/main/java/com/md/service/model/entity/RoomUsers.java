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
 * 房间用户
 * </p>
 */
@Getter
@Setter
@TableName("room_users")
@ApiModel(value = "RoomUsers对象", description = "房间用户")
public class RoomUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("房间唯一标识")
    private String roomNo;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("是否上麦 0 未上 1-8 在麦上的位置")
    private Integer onSeat;

    @ApiModelProperty("是否合唱 0 不合唱 1合唱")
    private Integer joinSing;

    @ApiModelProperty("是否开启摄像头 0 不开启 1开启")
    private Integer isVideoMuted;

    @ApiModelProperty("是否静音 0非静音 1静音")
    private Integer isSelfMuted;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    private Integer status;

    @TableLogic
    @ApiModelProperty("删除")
    private LocalDateTime deletedAt;


}
