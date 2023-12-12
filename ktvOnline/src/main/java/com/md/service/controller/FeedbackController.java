package com.md.service.controller;

import com.md.service.model.BaseResult;
import com.md.service.model.dto.FeedbackDTO;
import com.md.service.model.form.UploadFeedbackForm;
import com.md.service.service.FeedbackService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

@RestController
@RequestMapping("/feedback")
@Api(tags = "反馈")
@Slf4j
@EnableSwagger2
public class FeedbackController {
    @Resource
    private FeedbackService feedbackService;

    @PostMapping("/upload")
    @ApiOperation("提交反馈")
    public BaseResult<Void> upload(@RequestHeader("userNo") String userNo, @Validated @RequestBody UploadFeedbackForm form) {
        feedbackService.insertFeedback(FeedbackDTO.builder().
                screenshotURLs(form.getScreenshotURLs()).
                userNo(userNo).
                tags(form.getTags()).
                description(form.getDescription()).
                logURL(form.getLogURL()).
                build());
        return BaseResult.success();
    }
}
