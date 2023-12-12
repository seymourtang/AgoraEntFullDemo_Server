package com.md.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.md.service.model.dto.UserConfigLastBackgroundDTO;
import com.md.service.model.entity.UserConfig;
import com.md.service.model.entity.Users;
import com.md.service.model.form.CreateOrUpdateUserConfigForm;
import com.md.service.repository.UserConfigMapper;
import com.md.service.service.UserConfigService;
import com.md.service.service.UsersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserConfigServiceImpl extends ServiceImpl<UserConfigMapper, UserConfig> implements UserConfigService {
    @Resource
    private UsersService usersService;

    @Override
    public void createOrUpdateEntity(CreateOrUpdateUserConfigForm form) {
        Users user = usersService.getUserByNo(form.getUserNo());
        UserConfig toInsertOrUpdate = new UserConfig()
                .setUserNo(form.getUserNo())
                .setAppId(form.getAppId())
                .setMobile(user.getMobile())
                .setProjectId(form.getProjectId())
                .setSceneId(form.getSceneId())
                .setBackgroundUrl(form.getBackgroundUrl())
                .setBackgroundCount(1)
                .setCreatedAt(LocalDateTime.now())
                .setUpdatedAt(LocalDateTime.now());

        baseMapper.insertOrUpdate(toInsertOrUpdate);
    }

    @Override
    public UserConfigLastBackgroundDTO getLastBackgroundInfo(String appId, String userNo, String projectId, String sceneId, Integer lastDays) {
        Users user = usersService.getUserByNo(userNo);
        QueryWrapper<UserConfig> userConfigQueryWrapper = Wrappers.<UserConfig>query()
                .eq("mobile", user.getMobile())
                .eq("app_id", appId)
                .apply(true, "DATE_SUB(CURDATE(), INTERVAL " + lastDays + " DAY) <= date(updated_at)")
                .orderByDesc("updated_at");
        if (!StringUtils.isEmpty(sceneId)) {
            userConfigQueryWrapper.eq("scene_id", sceneId);
        }
        if (!StringUtils.isEmpty(projectId)) {
            userConfigQueryWrapper.eq("project_id", projectId);
        }
        UserConfigLastBackgroundDTO userConfigLastBackgroundDTO = new UserConfigLastBackgroundDTO();
        List<UserConfig> userConfigList = baseMapper.selectList(userConfigQueryWrapper);
        if (userConfigList == null || userConfigList.isEmpty()) {
            return userConfigLastBackgroundDTO;
        }
        int sum = userConfigList.stream().mapToInt(UserConfig::getBackgroundCount).sum();
        userConfigLastBackgroundDTO.setLastBackgroundUrl(userConfigList.get(0).getBackgroundUrl())
                .setCount(sum);
        return userConfigLastBackgroundDTO;
    }

    @Override
    public void clearUserConfig(String mobile) {
        baseMapper.delete(Wrappers.<UserConfig>query().lambda()
                .eq(UserConfig::getMobile, mobile));
    }
}
