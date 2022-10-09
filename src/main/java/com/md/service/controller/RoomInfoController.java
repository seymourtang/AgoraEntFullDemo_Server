package com.md.service.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.md.service.model.BaseResult;
import com.md.service.model.dto.RoomInfoDTO;
import com.md.service.model.dto.RoomPageDTO;
import com.md.service.model.form.RoomCreateForm;
import com.md.service.service.RoomInfoService;
import com.md.service.service.UsersService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

/**
 * <p>
 * 房间信息 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/roomInfo")
@Api(tags = "房间信息")
@Slf4j
@EnableSwagger2
public class RoomInfoController extends BaseController{

    @Resource
    private RoomInfoService roomInfoService;

    @Resource
    private UsersService usersService;

    @PostMapping("/createRoom")
    @ApiOperation("创建房间")
    public BaseResult<String> createRoom(@RequestBody RoomCreateForm form){
        log.info("createRoom form : {}",form);
        usersService.checkUserToken(getBaseUser(),form.getUserNo());
        return BaseResult.success(roomInfoService.createRoom(form));
    }

    @PostMapping("/roomList")
    @ApiOperation("房间列表")
    public BaseResult<IPage<RoomPageDTO>> roomList(@RequestBody Page form){
        log.info("roomList form : {}",form);
        return BaseResult.success(roomInfoService.roomList(form));
    }

    @PostMapping("/updateRoom")
    @ApiOperation("修改")
    public BaseResult<?> updateRoom(@RequestBody RoomCreateForm form){
        log.info("updateRoom form : {}",form);
        roomInfoService.updateRoom(form);
        return BaseResult.success();
    }

    @GetMapping("/getRoomInfo")
    @ApiOperation("获取+进入房间")
    public BaseResult<RoomInfoDTO> getRoomInfo(String roomNo,String userNo,String password){
        log.info("getRoomInfo form : {}",roomNo);
        usersService.checkUserToken(getBaseUser(),userNo);
        return BaseResult.success(roomInfoService.getRooInfo(roomNo,userNo,password));
    }

    @GetMapping("/outRoom")
    @ApiOperation("退出房间")
    public BaseResult<?> outRoom(String roomNo,String userNo){
        log.info("outRoom form : {}",roomNo);
        usersService.checkUserToken(getBaseUser(),userNo);
        roomInfoService.outRoom(roomNo,userNo);
        return BaseResult.success();
    }


    @GetMapping("/closeRoom")
    @ApiOperation("关闭房间")
    public BaseResult<?> closeRoom(String roomNo,String userNo){
        usersService.checkUserToken(getBaseUser(),userNo);
        log.info("closeRoom roomNo : {},userNo:{}",roomNo,userNo);
        roomInfoService.closeRoom(roomNo,userNo);
        return BaseResult.success();
    }

    @GetMapping("/onSeat")
    @ApiOperation("上麦")
    public BaseResult<String> onSeat(String roomNo,String userNo,Integer seat){
        usersService.checkUserToken(getBaseUser(),userNo);
        log.info("onSeat roomNo : {},userNo:{},seat:{}",roomNo,userNo,seat);
        roomInfoService.onSeat(roomNo,userNo,seat);
        return BaseResult.success();
    }

    @GetMapping("/outSeat")
    @ApiOperation("下麦")
    public BaseResult<String> outSeat(String roomNo,String userNo){
        usersService.checkUserToken(getBaseUser(),userNo);
        log.info("outSeat roomNo : {},userNo:{},seat:{}",roomNo);
        roomInfoService.outSeat(roomNo,userNo);
        return BaseResult.success();
    }

    @GetMapping("/getRoomNum")
    @ApiOperation("获取房间人数")
    public BaseResult<?> getRoomNum(String roomNo){
        log.info("getRoomNum roomNo :{}",roomNo);
        return BaseResult.success(roomInfoService.getRoomNum(roomNo));
    }
}
