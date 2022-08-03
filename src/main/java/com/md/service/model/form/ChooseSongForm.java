package com.md.service.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ChooseSongForm {

    @ApiModelProperty("user no")
    private String userNo;

    @ApiModelProperty("room no")
    private String roomNo;

    @ApiModelProperty("song no")
    private String songNo;

    @ApiModelProperty("song name")
    private String songName;

    @ApiModelProperty("song url")
    private String songUrl;

    @ApiModelProperty("imageUrl")
    private String imageUrl;

    @ApiModelProperty("作者")
    private String singer;

    @ApiModelProperty("是否合唱 0 不合唱 1合唱")
    private Integer isChorus;

    @ApiModelProperty("唱完打分")
    private Integer score = 0;
}
