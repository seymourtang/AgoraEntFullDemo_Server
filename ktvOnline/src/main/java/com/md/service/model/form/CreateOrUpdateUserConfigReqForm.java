package com.md.service.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class CreateOrUpdateUserConfigReqForm {

    @ApiModelProperty("App背景图片")
    private String backgroundUrl;

}
