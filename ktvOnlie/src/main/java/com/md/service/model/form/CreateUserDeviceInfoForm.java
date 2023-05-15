package com.md.service.model.form;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateUserDeviceInfoForm {

    @ApiModelProperty("用户no")
    private String userNo;

    @ApiModelProperty("AppId")
    private String appId;

    @ApiModelProperty("App 版本号")
    private String appVersion;

    @ApiModelProperty("projectId")
    private String projectId;

    @ApiModelProperty("sceneId")
    private String sceneId;

    @ApiModelProperty("os 版本号")
    private String osVersion;

    @ApiModelProperty("设备平台")
    private String platform;

    @ApiModelProperty("设备型号")
    private String model;

    @ApiModelProperty("制造商")
    private String manufacture;
}
