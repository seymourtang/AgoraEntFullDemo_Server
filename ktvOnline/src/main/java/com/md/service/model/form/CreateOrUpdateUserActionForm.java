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
public class CreateOrUpdateUserActionForm {
    @ApiModelProperty("用户no")
    private String userNo;

    @ApiModelProperty("AppId")
    private String appId;

    @ApiModelProperty("projectId")
    private String projectId;

    @ApiModelProperty("sceneId")
    private String sceneId;

    @ApiModelProperty("用户行为")
    private String action;

    @ApiModelProperty("用户行为次数")
    private Integer count;
}
