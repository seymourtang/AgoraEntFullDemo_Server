package com.md.service.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoomSongInfoDTO {

    @ApiModelProperty("歌曲")
    private String songNo;

    @ApiModelProperty("用户no")
    private String userNo;

    @ApiModelProperty("用户名字")
    private String name;

    @ApiModelProperty("合唱者")
    private String chorusNo;

    @ApiModelProperty("是否是合唱")
    private Boolean isChorus;

    @ApiModelProperty("排序")
    private Integer sort;

    @ApiModelProperty("作者")
    private String singer;

    @ApiModelProperty("歌词")
    private String lyric;

    @ApiModelProperty("是否是原唱 0 不是 1 原唱")
    private Integer isOriginal;

    @ApiModelProperty("1已唱 0 未唱 2正在唱")
    private Integer status;

    private String songUrl;

    private String imageUrl;

    private String songName;
}
