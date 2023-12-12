package com.md.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.md.service.model.dto.UserActionLastDTO;
import com.md.service.model.entity.UserActionInfo;
import com.md.service.model.entity.Users;
import com.md.service.model.form.CreateOrUpdateUserActionForm;
import com.md.service.repository.UserActionInfoMapper;
import com.md.service.service.UserActionInfoService;
import com.md.service.service.UsersService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
@Slf4j
public class UserActionInfoServiceImpl extends ServiceImpl<UserActionInfoMapper, UserActionInfo> implements UserActionInfoService {
    @Resource
    private UsersService usersService;

    @Override
    public void createOrUpdateEntity(CreateOrUpdateUserActionForm form) {
        Users user = usersService.getUserByNo(form.getUserNo());
        UserActionInfo toInsertOrUpdate = new UserActionInfo()
                .setUserNo(form.getUserNo())
                .setAppId(form.getAppId())
                .setMobile(user.getMobile())
                .setProjectId(form.getProjectId())
                .setDay(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE))
                .setSceneId(form.getSceneId())
                .setAction(form.getAction())
                .setCount(form.getCount())
                .setCreatedAt(LocalDateTime.now())
                .setUpdatedAt(LocalDateTime.now());

        baseMapper.insertOrUpdate(toInsertOrUpdate);
    }

    @Override
    public UserActionLastDTO getLastActionInfo(String appId, String userNo, String projectId, String sceneId, Integer lastDays) {
        Users user = usersService.getUserByNo(userNo);
        QueryWrapper<UserActionInfo> userActionInfoQueryWrapper = Wrappers.<UserActionInfo>query()
                .eq("mobile", user.getMobile())
                .eq("app_id", appId)
                .apply(true, "DATE_SUB(CURDATE(), INTERVAL " + lastDays + " DAY) <= date(updated_at)")
                .orderByDesc("updated_at");
        if (!StringUtils.isEmpty(sceneId)) {
            userActionInfoQueryWrapper.eq("scene_id", sceneId);
        } else {
            userActionInfoQueryWrapper.ne("scene_id", "");
        }
        if (!StringUtils.isEmpty(projectId)) {
            userActionInfoQueryWrapper.eq("project_id", projectId);
        } else {
            userActionInfoQueryWrapper.eq("project_id", "");
        }
        List<UserActionInfo> userActionInfoList = baseMapper.selectList(userActionInfoQueryWrapper);
        log.debug("userActionInfoList:{}", userActionInfoList);
        if (userActionInfoList == null || userActionInfoList.isEmpty()) {
            return new UserActionLastDTO();
        }
        int sum = userActionInfoList.stream().mapToInt(UserActionInfo::getCount).sum();
        return new UserActionLastDTO()
                .setLastAction(userActionInfoList.get(0).getAction())
                .setCount(sum);
    }

    @Override
    public void clearUserActionInfo(String mobile) {
        baseMapper.delete(Wrappers.<UserActionInfo>query().lambda()
                .eq(UserActionInfo::getMobile, mobile));
    }
}
