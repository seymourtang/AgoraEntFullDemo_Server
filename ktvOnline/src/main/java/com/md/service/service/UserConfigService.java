package com.md.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.md.service.model.dto.UserConfigLastBackgroundDTO;
import com.md.service.model.entity.UserConfig;
import com.md.service.model.form.CreateOrUpdateUserConfigForm;

public interface UserConfigService extends IService<UserConfig> {
    /**
     * 创建或者更新用户配置
     *
     * @param form
     */
    void createOrUpdateEntity(CreateOrUpdateUserConfigForm form);

    /**
     * 获取用户设备记录列表
     *
     * @param appId
     * @param userNo
     * @param projectId
     * @param sceneId
     * @param lastDays
     */
    UserConfigLastBackgroundDTO getLastBackgroundInfo(String appId, String userNo, String projectId, String sceneId, Integer lastDays);
}
