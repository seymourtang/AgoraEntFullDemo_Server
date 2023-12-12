package com.md.service.controller;

import cn.hutool.json.JSONObject;
import com.md.service.model.BaseResult;
import com.md.service.utils.MdStringUtils;
import com.md.service.utils.UploadFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

@RestController
@RequestMapping("/upload")
@Api(tags = "上传图片")
@Slf4j
@EnableSwagger2
public class UploadFileController {
    @Resource
    private UploadFile uploadFile;

    @ApiOperation(value = "上传图片")
    @PostMapping()
    public BaseResult<?> image(@RequestHeader("userNo") String userNo, @RequestParam("file") MultipartFile file) {
        log.info("upload file userNo:{}", userNo);
        String fileName = file.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        String name = System.currentTimeMillis() + MdStringUtils.randomDelete("random", 5) + prefix;
        String url = "";
        url = uploadFile.uploadFile(file, name);
        JSONObject obj = new JSONObject();
        obj.set("url", url);
        return BaseResult.success(obj);
    }

    @ApiOperation(value = "上传日志")
    @PostMapping("/log")
    public BaseResult<?> log(@RequestHeader("userNo") String userNo, @RequestParam("file") MultipartFile file) {
        log.info("upload file userNo:{}", userNo);
        String fileName = file.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        String name = System.currentTimeMillis() + MdStringUtils.randomDelete("random", 5) + prefix;
        String url = "";
        url = uploadFile.uploadLog(file, name);
        JSONObject obj = new JSONObject();
        obj.set("url", url);
        return BaseResult.success(obj);
    }
}
