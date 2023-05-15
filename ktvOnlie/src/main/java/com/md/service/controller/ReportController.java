package com.md.service.controller;

import com.md.service.common.DeviceRangeType;
import com.md.service.model.BaseResult;
import com.md.service.model.dto.UserDeviceInfoListDTO;
import com.md.service.model.form.CreateUserDeviceInfoForm;
import com.md.service.model.form.CreateUserDeviceInfoReqForm;
import com.md.service.service.UserDeviceInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import javax.annotation.Resource;

@RestController
@RequestMapping("/report")
@Api(tags = "设备隐私信息")
@Slf4j
@EnableSwagger2
public class ReportController {
    @Resource
    private UserDeviceInfoService userDeviceInfoService;

    @ApiOperation(value = "上报设备信息")
    @PostMapping("/device")
    public BaseResult<?> report(String userNo, String sceneId, String appId, String projectId, @RequestBody CreateUserDeviceInfoReqForm reqForm) {
        log.info("user post report device infos, name :");
        CreateUserDeviceInfoForm form = new CreateUserDeviceInfoForm();
        form.setUserNo(userNo);
        form.setSceneId(sceneId);
        form.setAppId(appId);
        form.setProjectId(projectId);
        form.setManufacture(reqForm.getManufacture());
        form.setModel(reqForm.getModel());
        form.setPlatform(reqForm.getPlatform());
        form.setAppVersion(reqForm.getAppVersion());
        form.setOsVersion(reqForm.getOsVersion());
        userDeviceInfoService.createInfo(form);
        return BaseResult.success();
    }


    @ApiOperation(value = "拉取用户设备信息")
    @GetMapping("/device")
    public BaseResult<?> list(String userNo, String sceneId, String appId, String projectId, Integer last) {
        log.info("user get report device infos, name :");
        DeviceRangeType rangeType = DeviceRangeType.getEnumValue(last);
        if (rangeType == null) {
            rangeType = DeviceRangeType.Last7Days;
        }
        UserDeviceInfoListDTO userDeviceInfoListDTO = userDeviceInfoService.getList(appId, userNo, projectId, sceneId, rangeType.getLastDays());
        return BaseResult.success(userDeviceInfoListDTO);
    }

}
