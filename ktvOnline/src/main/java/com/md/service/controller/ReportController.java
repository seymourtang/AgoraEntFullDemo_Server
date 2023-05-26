package com.md.service.controller;

import com.md.service.common.DeviceRangeType;
import com.md.service.model.BaseResult;
import com.md.service.model.dto.UserActionLastDTO;
import com.md.service.model.dto.UserConfigLastBackgroundDTO;
import com.md.service.model.dto.UserDeviceInfoListDTO;
import com.md.service.model.form.*;
import com.md.service.service.UserActionInfoService;
import com.md.service.service.UserConfigService;
import com.md.service.service.UserDeviceInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

@RestController
@RequestMapping("/report")
@Api(tags = "设备隐私信息")
@Slf4j
@EnableSwagger2
@Validated
public class ReportController {
    @Resource
    private UserDeviceInfoService userDeviceInfoService;

    @Resource
    private UserConfigService userConfigService;

    @Resource
    private UserActionInfoService userActionInfoService;

    @ApiOperation(value = "上报设备信息")
    @PostMapping("/device")
    public BaseResult<?> reportDeviceInfo(String userNo, String sceneId, String appId, String projectId, @RequestBody CreateUserDeviceInfoReqForm reqForm) {
        log.info("user post report device infos, name :");
        CreateUserDeviceInfoForm form = new CreateUserDeviceInfoForm()
                .setUserNo(userNo)
                .setSceneId(sceneId)
                .setAppId(appId)
                .setProjectId(projectId)
                .setManufacture(reqForm.getManufacture())
                .setModel(reqForm.getModel())
                .setPlatform(reqForm.getPlatform())
                .setAppVersion(reqForm.getAppVersion())
                .setOsVersion(reqForm.getOsVersion());
        userDeviceInfoService.createInfo(form);
        return BaseResult.success();
    }


    @ApiOperation(value = "拉取用户设备信息")
    @GetMapping("/device")
    public BaseResult<?> listDeviceInfo(String userNo, String sceneId, String appId, String projectId, Integer last) {
        log.info("user get report device infos, name :");
        DeviceRangeType rangeType = DeviceRangeType.getEnumValue(last);
        if (rangeType == null) {
            rangeType = DeviceRangeType.Last7Days;
        }
        UserDeviceInfoListDTO userDeviceInfoListDTO = userDeviceInfoService.getList(appId, userNo, projectId, sceneId, rangeType.getLastDays());
        return BaseResult.success(userDeviceInfoListDTO);
    }


    @ApiOperation(value = "上报用户使用的背景图片信息")
    @PostMapping("/background")
    public BaseResult<?> reportBackground(String userNo, String sceneId, String appId, String projectId, @RequestBody CreateOrUpdateUserConfigReqForm reqForm) {
        log.info("user report background infos, name :");
        CreateOrUpdateUserConfigForm form = new CreateOrUpdateUserConfigForm()
                .setUserNo(userNo)
                .setAppId(appId)
                .setProjectId(projectId)
                .setSceneId(sceneId)
                .setBackgroundUrl(reqForm.getBackgroundUrl());
        userConfigService.createOrUpdateEntity(form);
        return BaseResult.success();
    }

    @ApiOperation(value = "拉取用户使用的背景图片信息")
    @GetMapping("/background")
    public BaseResult<?> getBackground(String userNo, String sceneId, String appId, String projectId, Integer last) {
        log.info("user get background infos, userNo:{},sceneId:{},appId:{},projectId:{},last:{}", userNo, sceneId, appId, projectId, last);
        DeviceRangeType rangeType = DeviceRangeType.getEnumValue(last);
        if (rangeType == null) {
            rangeType = DeviceRangeType.Last7Days;
        }
        UserConfigLastBackgroundDTO userConfigLastBackgroundDTO = userConfigService.getLastBackgroundInfo(appId, userNo, projectId, sceneId, rangeType.getLastDays());
        return BaseResult.success(userConfigLastBackgroundDTO);
    }

    @ApiOperation(value = "上报用户行为记录")
    @PostMapping("/action")
    public BaseResult<?> reportAction(String userNo, String sceneId, String appId, String projectId, @Validated @RequestBody CreateOrUpdateUserActionReqForm reqForm) {
        log.info("user report action infos, name :");
        CreateOrUpdateUserActionForm form = new CreateOrUpdateUserActionForm()
                .setUserNo(userNo)
                .setAppId(appId)
                .setProjectId(projectId)
                .setSceneId(sceneId)
                .setAction(reqForm.getAction())
                .setCount(1);
        userActionInfoService.createOrUpdateEntity(form);
        return BaseResult.success();
    }

    @ApiOperation(value = "拉取用户行为记录")
    @GetMapping("/action")
    public BaseResult<?> getAction(String userNo, String sceneId, String appId, String projectId, Integer last) {
        log.info("user get action infos, userNo:{},sceneId:{},appId:{},projectId:{},last:{}", userNo, sceneId, appId, projectId, last);
        DeviceRangeType rangeType = DeviceRangeType.getEnumValue(last);
        if (rangeType == null) {
            rangeType = DeviceRangeType.Last7Days;
        }
        UserActionLastDTO userActionLastDTO = userActionInfoService.getLastActionInfo(appId, userNo, projectId, sceneId, rangeType.getLastDays());
        return BaseResult.success(userActionLastDTO);
    }
}
