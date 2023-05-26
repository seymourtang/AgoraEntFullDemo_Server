package com.md.service.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class CreateOrUpdateUserConfigForm {
    @ApiModelProperty("用户no")
    private String userNo;

    @ApiModelProperty("AppId")
    private String appId;

    @ApiModelProperty("projectId")
    private String projectId;

    @ApiModelProperty("sceneId")
    private String sceneId;

    @ApiModelProperty("App背景图片")
    private String backgroundUrl;
}
