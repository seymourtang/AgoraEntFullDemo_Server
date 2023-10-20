package com.md.service.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@TableName("user_device_info")
@ApiModel(value = "UserDeviceInfo对象", description = "用户设备信息")
public class UserDeviceInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户no")
    private String userNo;

    @ApiModelProperty("用户手机号")
    private String mobile;

    @ApiModelProperty("AppId")
    private String appId;

    @ApiModelProperty("projectId")
    private String projectId;

    @ApiModelProperty("sceneId")
    private String sceneId;

    @ApiModelProperty("App 版本号")
    private String appVersion;

    @ApiModelProperty("os 版本号")
    private String osVersion;

    @ApiModelProperty("设备平台")
    private String platform;

    @ApiModelProperty("设备型号")
    private String model;

    @ApiModelProperty("制造商")
    private String manufacture;

    @ApiModelProperty("删除")
    @TableLogic
    private LocalDateTime deletedAt;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
