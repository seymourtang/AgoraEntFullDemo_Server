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
@TableName("user_config")
@ApiModel(value = "UserConfig对象", description = "用户配置信息")
public class UserConfig implements Serializable {
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

    @ApiModelProperty("最近的一次App背景图片")
    @TableField(value = "background_url")
    private String backgroundUrl;

    @ApiModelProperty("收集用户背景图片次数")
    @TableField(value = "background_count")
    private Integer backgroundCount;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
