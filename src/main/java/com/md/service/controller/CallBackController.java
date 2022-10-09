package com.md.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.md.service.config.RtmJavaClient;
import com.md.service.model.BaseResult;
import com.md.service.model.entity.Users;
import com.md.service.model.form.ChooseSongForm;
import com.md.service.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

@RestController
@RequestMapping("/callBack")
@Api(tags = "回调")
@Slf4j
@EnableSwagger2
public class CallBackController {

    @Resource
    private RtmJavaClient rtmJavaClient;

    @Resource
    private UsersService usersService;

    @PostMapping("/audit")
    @ApiOperation("语音审核回调")
    public BaseResult<String> audit(@RequestBody String channelName , String uid, String streamType){
        log.info("channelName : {} , uid:{} ,streamType:{} ",channelName,uid,streamType);
        JSONObject result = JSONObject.parseObject(channelName);
        if(!result.getString("result").equals("pass")){
            JSONObject audioId = result.getJSONObject("audioId");
            String roomNo = audioId.getString("channelName");
            Integer userId = audioId.getInteger("uid");
            Users users = usersService.getById(userId);
            rtmJavaClient.login("system_admin",roomNo);
            JSONObject object = new JSONObject();
            object.put("error","error");
            rtmJavaClient.sendMessagePeer(users.getUserNo(),object.toString());
        }

        return BaseResult.success();
    }
}
