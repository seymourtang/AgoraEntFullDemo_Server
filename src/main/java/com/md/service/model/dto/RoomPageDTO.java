package com.md.service.model.dto;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class RoomPageDTO {



    @ApiModelProperty("房间名字")
    private String name;

    @ApiModelProperty("是否公开 0 公开 1加密")
    private Integer isPrivate;

//    @ApiModelProperty("密码")
//    private String password;

    @ApiModelProperty("房间唯一标识")
    private String roomNo;

    @ApiModelProperty("创建用户")
    private String creatorNo;

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

    @ApiModelProperty("房间状态 0 正常 1关闭 2异常")
    private Integer status;

    @ApiModelProperty("房间人数")
    private Long roomPeopleNum;

}
