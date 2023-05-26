package com.md.service.controller;

import com.md.service.model.BaseResult;
import com.md.service.service.RoomUsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 房间用户 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/roomUsers")
@Slf4j
@Api(tags = "房间用户")
public class RoomUsersController {

    @Resource
    private RoomUsersService roomUsersService;

    @GetMapping("/openCamera")
    @ApiOperation("开摄像头")
    public BaseResult<String> isVideoMuted(String roomNo,String userNo){
        roomUsersService.isVideoMuted(roomNo,userNo);
        return BaseResult.success();
    }

    @GetMapping("/ifQuiet")
    @ApiOperation("是否静音")
    public BaseResult<String> isSelfMuted(String roomNo,String userNo){
        roomUsersService.isSelfMuted(roomNo,userNo);
        return BaseResult.success();
    }
}
