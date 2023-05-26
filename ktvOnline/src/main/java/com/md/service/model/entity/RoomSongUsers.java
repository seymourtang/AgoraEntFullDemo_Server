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
 * 房间歌曲演唱者
 * </p>
 */
@Getter
@Setter
@TableName("room_song_users")
@ApiModel(value = "RoomSongUsers对象", description = "房间歌曲演唱者")
public class RoomSongUsers implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("房间唯一标识")
    private String roomNo;

    @ApiModelProperty("歌曲id")
    private Integer roomSongId;

    @ApiModelProperty("用户id")
    private Integer userId;

    @ApiModelProperty("得分")
    private Integer score;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @ApiModelProperty("1已唱 0 未唱")
    private Integer status;

    @TableLogic
    @ApiModelProperty("删除")
    private LocalDateTime deletedAt;


}
