package com.md.service.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdateUserInfoForm {

    @ApiModelProperty("用户no")
    private String userNo;

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("用户头像")
    private String headUrl;

    @ApiModelProperty("用户性别 w 女 m 男 x 未知")
    private String sex;
}
