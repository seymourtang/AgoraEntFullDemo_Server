package com.md.service.model.entity;

import com.baomidou.mybatisplus.annotation.*;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@TableName("feedback")
@ApiModel(value = "feedback对象", description = "feedback对象")
public class Feedback implements Serializable {
    @ApiModelProperty("id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("用户ID")
    @TableField(value = "user_no")
    private String userNo;

    @ApiModelProperty("昵称")
    @TableField(value = "name")
    private String name;

    @ApiModelProperty("反馈标签")
    @TableField(value = "tags")
    private String tags;

    @ApiModelProperty("反馈描述")
    @TableField(value = "description")
    private String description;

    @ApiModelProperty("反馈截图URL列表")
    @TableField(value = "screenshot_urls")
    private String screenshotURLs;

    @ApiModelProperty("上传日志URL")
    @TableField(value = "log_url")
    private String logURL;

    @ApiModelProperty("创建时间")
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createdAt;

    @ApiModelProperty("修改时间")
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updatedAt;
}
