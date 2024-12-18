package com.md.service.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@ApiModel(value = "UserVerificationLog对象", description = "用户认证日志信息")
public class UserVerificationLog implements Serializable {

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty(value = "成功认证的批次号")
    private String verifyBatchId;

    @ApiModelProperty(value = "用户no")
    private String userNo;

    @ApiModelProperty(value = "认证结果:1通过,2失败")
    private int verifyResult;

    @ApiModelProperty(value = "加密后的真实姓名")
    private String realNameCipher;

    @ApiModelProperty(value = "加密后的身份证号")
    private String idCardCipher;

    @ApiModelProperty(value = "认证备注/失败原因")
    private String verifyRemark;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createdAt;

    @ApiModelProperty(value = "最近修改时间")
    private LocalDateTime updatedAt;
}
