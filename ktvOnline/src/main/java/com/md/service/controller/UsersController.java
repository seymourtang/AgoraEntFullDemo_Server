package com.md.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.md.service.model.BaseResult;
import com.md.service.model.dto.UserInfo;
import com.md.service.model.entity.Users;
import com.md.service.model.form.UpdateUserInfoForm;
import com.md.service.model.form.UserRealNameAuthForm;
import com.md.service.service.UsersService;
import com.md.service.utils.RtmTokenBuilderSample;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

/**
 * <p>
 * 用户信息 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/users")
@Slf4j
@Api(tags = "用户管理")
@EnableSwagger2
public class UsersController extends BaseController {

    @Resource
    private UsersService usersService;

    @Resource
    private RtmTokenBuilderSample rtmTokenBuilderSample;

    @GetMapping("/verificationCode")
    @ApiOperation("发送验证码")
    public BaseResult<String> verificationCode(String phone) {
        log.info("verificationCode form : {}", phone);
        BaseResult<String> result = usersService.verificationCode(phone);
        log.info("verificationCode result :{}", result);
        return result;
    }

    @GetMapping("/login")
    @ApiOperation("登录")
    public BaseResult<UserInfo> login(String phone, String code) {
        log.info("login form : {}", phone);
        BaseResult<UserInfo> result = usersService.login(phone, code);
        log.info("login result :{}", result);
        return result;
    }

    @PostMapping("/update")
    @ApiOperation("修改用户信息")
    public BaseResult<UserInfo> update(@RequestHeader("userNo") String userNo, @RequestBody UpdateUserInfoForm form) {
        form.setUserNo(userNo);
        log.info("update form : {}", form);
        BaseResult<UserInfo> result = usersService.updateInfo(form);
        log.info("update result :{}", result);
        return result;
    }

    @GetMapping("/cancellation")
    @ApiOperation("注销用户")
    public BaseResult<?> cancellation(@RequestHeader("userNo") String userNo) {
        log.info("cancellation userNo:{}", userNo);
        BaseResult<?> result = usersService.cancellation(userNo);
        log.info("cancellation userNo:{},result :{}", userNo, result);
        return result;
    }

    @GetMapping("/getUserInfo")
    @ApiOperation("获取用户信息")
    public BaseResult<?> getUserInfo(@RequestHeader("userNo") String userNo) {
        BaseResult<?> result = usersService.getUserInfo(userNo);
        log.info("getUserInfo userNo:{},result:{}", userNo, result);
        return result;
    }

    @PostMapping("/userList")
    @ApiOperation("用户列表")
    public BaseResult<IPage<Users>> roomList(@RequestHeader("userNo") String userNo, @RequestBody Page form) {
        log.info("roomList userNo:{},form:{}", userNo, form);
        return BaseResult.success(usersService.userList(form));
    }

    @GetMapping("/getToken")
    @ApiOperation("获取token")
    public BaseResult<?> getToken(@RequestHeader("userNo") String userNo, Integer userId, String roomNo) throws Exception {
        JSONObject result = new JSONObject();
        if (StringUtils.isEmpty(roomNo)) {
            roomNo = "";
        }
        String token = rtmTokenBuilderSample.getToken(userId, roomNo);
        String rtcToken = rtmTokenBuilderSample.getRtcToken(userId, roomNo);
        result.put("token", token);
        result.put("rtcToken", rtcToken);
        log.info("getUserInfo userNo:{},result :{}", userNo, result);
        return BaseResult.success(result);
    }

    @PostMapping("/realNameAuth")
    @ApiOperation("实名认证")
    public BaseResult<?> realNameAuth(@RequestHeader("userNo") String userNo, @Validated @RequestBody UserRealNameAuthForm form) throws Exception {
        log.info("realNameAuth userNo:{},form:{}", userNo, form);
        usersService.realNameAuth(userNo, form.getRealName(), form.getIdCard());

        return BaseResult.success();
    }
}
