package com.md.service.controller;

import cn.hutool.json.JSONObject;
import com.md.service.common.ErrorCodeEnum;
import com.md.service.exception.BaseException;
import com.md.service.model.BaseResult;
import com.md.service.utils.MdStringUtils;
import com.md.service.utils.UploadFile;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
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
    public BaseResult<?> file(@RequestParam("file") MultipartFile file){
        String fileName = file.getOriginalFilename();
        String prefix = fileName.substring(fileName.lastIndexOf("."));
        String name = System.currentTimeMillis() + MdStringUtils.randomDelete("random",5) + prefix;
        String url = "";
        try {
            url = uploadFile.uploadFile(file,name);
            JSONObject obj = new JSONObject();
            obj.set("url",url);
            return BaseResult.success(obj);
        } catch (Exception e) {
            throw new BaseException(ErrorCodeEnum.upload_failed);
        }
    }
}
