package com.md.service.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoomUserInfoDTO {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("是否上麦 0 未上 1-8 在麦上的位置")
    private Integer onSeat;

    @ApiModelProperty("是否合唱 0 不合唱 1合唱")
    private Integer joinSing;

    @ApiModelProperty("用户no")
    private String userNo;

    @ApiModelProperty("是否开启摄像头 0 不开启 1开启")
    private Integer isVideoMuted;

    @ApiModelProperty("是否静音 0非静音 1静音")
    private Integer isSelfMuted;

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("用户头像")
    private String headUrl;

    @ApiModelProperty("是否是房主")
    private Boolean isMaster = false;

}
