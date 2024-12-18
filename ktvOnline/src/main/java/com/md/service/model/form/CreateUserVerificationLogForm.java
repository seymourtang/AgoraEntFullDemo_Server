package com.md.service.model.form;

import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateUserVerificationLogForm {
    @ApiModelProperty("用户实名认证批次号")
    private String verifyBatchId;

    @ApiModelProperty("用户no")
    private String userNo;

    @ApiModelProperty("用户实名认证状态1通过，2失败")
    private int verifyResult;

    @ApiModelProperty("加密后的真实姓名")
    private String realNameCipher;

    @ApiModelProperty("加密后的身份证号")
    private String idCardCipher;

    @ApiModelProperty("认证备注/失败原因")
    private String verifyRemark;
}
