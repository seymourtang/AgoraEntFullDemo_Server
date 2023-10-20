package com.md.service.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Accessors(chain = true)
@TableName("user_action_info")
@ApiModel(value = "UserActionInfo对象", description = "用户行为统计信息")
public class UserActionInfo implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户no")
    private String userNo;

    @ApiModelProperty("用户手机号")
    @TableField(value = "mobile")
    private String mobile;

    @ApiModelProperty("AppId")
    private String appId;

    @ApiModelProperty("projectId")
    private String projectId;

    @ApiModelProperty("sceneId")
    private String sceneId;

    @ApiModelProperty("day")
    private String day;

    @ApiModelProperty("action")
    private String action;

    @ApiModelProperty("收集的行为次数")
    private Integer count;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}

