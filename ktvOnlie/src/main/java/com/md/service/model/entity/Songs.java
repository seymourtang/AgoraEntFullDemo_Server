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
 * 歌曲信息
 * </p>
 */
@Getter
@Setter
@ApiModel(value = "Songs对象", description = "歌曲信息")
public class Songs implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("歌曲")
    private String songNo;

    @ApiModelProperty("歌曲名称")
    private String songName;

    @ApiModelProperty("歌曲图片")
    private String songUrl;

    @ApiModelProperty("作者")
    private String singer;

    @ApiModelProperty("歌词")
    private String lyric;

    @ApiModelProperty("类型")
    private String type;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;

    @ApiModelProperty("1已唱 0 未唱")
    private Integer status;

    @ApiModelProperty("图片url")
    private String imageUrl;

    @TableLogic
    @ApiModelProperty("删除")
    private LocalDateTime deletedAt;


}
