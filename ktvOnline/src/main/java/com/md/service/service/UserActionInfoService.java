package com.md.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.md.service.model.dto.UserActionLastDTO;
import com.md.service.model.entity.UserActionInfo;
import com.md.service.model.form.CreateOrUpdateUserActionForm;

public interface UserActionInfoService extends IService<UserActionInfo> {
    /**
     * 创建或者更新行为
     *
     * @param form
     */
    void createOrUpdateEntity(CreateOrUpdateUserActionForm form);
    /**
     * 获取最近的用户行为
     *
     * @param appId
     * @param userNo
     * @param projectId
     * @param sceneId
     * @param lastDays
     */
    UserActionLastDTO getLastActionInfo(String appId, String userNo, String projectId, String sceneId, Integer lastDays);

    void clearUserActionInfo(String mobile);
}
