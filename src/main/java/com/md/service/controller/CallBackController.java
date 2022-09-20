package com.md.service.controller;

import com.md.service.model.BaseResult;
import com.md.service.model.form.ChooseSongForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@RestController
@RequestMapping("/callBack")
@Api(tags = "回到")
@Slf4j
@EnableSwagger2
public class CallBackController {

    @PostMapping("/audit")
    @ApiOperation("语音审核回调")
    public BaseResult<String> audit(@RequestBody String channelName , String uid, String streamType){
        log.info("channelName : {} , uid:{} ,streamType:{} ",channelName,uid,streamType);
        return BaseResult.success();
    }
}
