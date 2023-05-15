package com.md.service.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeviceItemDTO {
    @ApiModelProperty("名称")
    private String name;

    @ApiModelProperty("数量")
    private Long total;
}
