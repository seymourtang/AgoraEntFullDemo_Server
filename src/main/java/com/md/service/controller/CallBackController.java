package com.md.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.md.service.config.RtmJavaClient;
import com.md.service.model.BaseResult;
import com.md.service.model.dto.UserInfo;
import com.md.service.model.entity.Users;
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
            rtmJavaClient.login("99999999");
            UserInfo userInfo = usersService.getUser(users.getUserNo());
            JSONObject object = new JSONObject();
            object.put("userNo",userInfo.getUserNo());
            object.put("messageType","20");
            object.put("roomNo",roomNo);
            object.put("reason","audit fail");
            rtmJavaClient.sendMessage(roomNo,object.toString());
        }
        return BaseResult.success();
    }

    @GetMapping("/test")
    @ApiOperation("语音审核回调")
    public BaseResult<String> test(String roomNo , String userNo){
        rtmJavaClient.login("99999999");
        JSONObject object = new JSONObject();
        object.put("userNo",userNo);
        object.put("messageType","20");
        object.put("roomNo",roomNo);
        object.put("reason","audit fail");
        rtmJavaClient.sendMessage(roomNo,object.toString());
        return BaseResult.success();
    }

}
