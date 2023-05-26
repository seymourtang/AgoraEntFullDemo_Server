package com.md.service.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserConfigLastBackgroundDTO {
    @ApiModelProperty("最近的背景图片URL")
    private String lastBackgroundUrl;

    @ApiModelProperty("总的收集次数")
    private Integer count;
}
