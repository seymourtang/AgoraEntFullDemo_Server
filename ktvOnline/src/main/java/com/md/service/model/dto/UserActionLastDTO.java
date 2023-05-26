package com.md.service.model.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class UserActionLastDTO {
    @ApiModelProperty("最近的用户行为")
    private String lastAction;

    @ApiModelProperty("总的收集次数")
    private Integer count;
}
