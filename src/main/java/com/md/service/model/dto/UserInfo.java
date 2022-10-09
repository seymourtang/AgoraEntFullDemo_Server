package com.md.service.model.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "Users对象", description = "用户信息")
public class UserInfo {

    @ApiModelProperty("id")
    private Integer id;

    @ApiModelProperty("用户openId")
    private String openId;

    @ApiModelProperty("用户no")
    private String userNo;

    @ApiModelProperty("用户名称")
    private String name;

    @ApiModelProperty("用户头像")
    private String headUrl;

//    @ApiModelProperty("用户性别 w 女 m 男 x 未知")
//    private String sex;

//    @ApiModelProperty("用户手机号")
//    private String mobile;

    @ApiModelProperty("用户状态")
    private Integer status;

    private String token;
}
