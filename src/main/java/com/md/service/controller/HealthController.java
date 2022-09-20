package com.md.service.controller;

import com.md.service.model.BaseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/")
@Api(tags = "健康检查")
@Slf4j
@EnableSwagger2
public class HealthController {

    @GetMapping("/health")
    @ApiOperation("健康检查")
    public BaseResult<String> health(){
        return BaseResult.success();
    }
}
