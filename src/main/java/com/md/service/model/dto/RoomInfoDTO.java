package com.md.service.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class RoomInfoDTO {

    @ApiModelProperty("房间名字")
    private String name;

    @ApiModelProperty("创建用户")
    private String creatorNo;

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

    @ApiModelProperty("agoraRTMToken")
    private String agoraRTMToken;

    @ApiModelProperty("agoraRTCToken")
    private String agoraRTCToken;

    @ApiModelProperty("agoraPlayerRTCToken")
    private String agoraPlayerRTCToken;

    private String code = "getRoomInfo";

    private Long time = System.currentTimeMillis();

    private List<RoomUserInfoDTO> roomUserInfoDTOList;

    private List<RoomSongInfoDTO> roomSongInfoDTOS;

}
