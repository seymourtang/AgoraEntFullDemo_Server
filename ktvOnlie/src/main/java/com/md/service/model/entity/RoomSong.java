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
 * 房间歌曲
 * </p>
 */
@Getter
@Setter
@TableName("room_song")
@ApiModel(value = "RoomSong对象", description = "房间歌曲")
public class RoomSong implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("房间唯一标识")
    private String roomNo;

    @ApiModelProperty("歌曲")
    private String songNo;

    @ApiModelProperty("用户no")
    private String userNo;

    @ApiModelProperty("合唱者")
    private String chorusNo;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("是否是原唱 0 不是 1 原唱")
    private Integer isOriginal;

    @ApiModelProperty("是否切歌了 0 没切 1切了")
    private String isSwitch;

    @ApiModelProperty("是否合唱 0 不合唱 1合唱")
    private Integer isChorus;

    @ApiModelProperty("得分")
    private Integer score;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @ApiModelProperty("1已唱 0 未唱 2正在唱")
    private Integer status;

    @TableLogic
    @ApiModelProperty("删除")
    private LocalDateTime deletedAt;


}
