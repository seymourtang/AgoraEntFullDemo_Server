package com.md.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.md.service.model.dto.UserDeviceInfoListDTO;
import com.md.service.model.entity.UserDeviceInfo;
import com.md.service.model.form.CreateUserDeviceInfoForm;

public interface UserDeviceInfoService extends IService<UserDeviceInfo> {

    /**
     * 创建用户设备记录
     *
     * @param form
     */
    void createInfo(CreateUserDeviceInfoForm form);


    /**
     * 获取用户设备记录列表
     *
     * @param appId
     * @param userNo
     * @param sceneId
     * @param projectId
     * @param lastDays
     */
    UserDeviceInfoListDTO getList(String appId, String userNo, String projectId, String sceneId, Integer lastDays);
}
