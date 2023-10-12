package com.md.service.controller;


import com.md.service.common.MetaDataEnum;
import com.md.service.exception.BaseException;
import com.md.service.model.BaseResult;
import com.md.service.service.MetaDataService;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;

@RestController
@Api(tags = "metaData信息")
@Slf4j
@EnableSwagger2
@RequestMapping(value = "/metaData", produces = MediaType.APPLICATION_JSON_VALUE)
public class MetaDataController {
    @Resource
    MetaDataService metaDataService;

    @GetMapping("/{key}")
    public BaseResult<?> getMetaData(@NotNull @PathVariable int key) {
        MetaDataEnum metaDataEnum = MetaDataEnum.getEnumValue(key);
        if (metaDataEnum == null) {
            throw new BaseException(403, "invalid key");
        }
        return BaseResult.success(metaDataService.getMetaData(metaDataEnum.getKey()));
    }
}
