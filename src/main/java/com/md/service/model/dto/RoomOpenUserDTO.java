package com.md.service.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RoomOpenUserDTO {

    private String roomNo;

    private Integer userId;

    @ApiModelProperty("是否上麦 0 未上 1-8 在麦上的位置")
    private Integer onSeat;
}
