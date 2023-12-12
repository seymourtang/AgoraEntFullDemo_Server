package com.md.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.md.service.model.dto.DeviceItemDTO;
import com.md.service.model.dto.UserDeviceInfoListDTO;
import com.md.service.model.entity.UserDeviceInfo;
import com.md.service.model.entity.Users;
import com.md.service.model.form.CreateUserDeviceInfoForm;
import com.md.service.repository.UserDeviceInfoMapper;
import com.md.service.service.UserDeviceInfoService;
import com.md.service.service.UsersService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class UserDeviceInfoServiceImpl extends ServiceImpl<UserDeviceInfoMapper, UserDeviceInfo> implements UserDeviceInfoService {
    @Resource
    private UsersService usersService;

    @Resource
    private UserDeviceInfoMapper userDeviceInfoMapper;

    @Override
    public void createInfo(CreateUserDeviceInfoForm form) {
        Users users = usersService.getUserByNo(form.getUserNo());

        UserDeviceInfo userDeviceInfo = new UserDeviceInfo()
                .setUserNo(users.getUserNo())
                .setMobile(users.getMobile())
                .setAppId(form.getAppId())
                .setProjectId(form.getProjectId())
                .setSceneId(form.getSceneId())
                .setAppVersion(form.getAppVersion())
                .setOsVersion(form.getOsVersion())
                .setPlatform(form.getPlatform())
                .setModel(form.getModel())
                .setManufacture(form.getManufacture());

        baseMapper.insert(userDeviceInfo);
    }

    @Override
    public UserDeviceInfoListDTO getList(String appId, String userNo, String projectId, String sceneId, Integer lastDays) {
        Users users = usersService.getUserByNo(userNo);

        UserDeviceInfoListDTO userDeviceInfoListDTO = new UserDeviceInfoListDTO();
        QueryWrapper<UserDeviceInfo> modelQueryWrapper = Wrappers.<UserDeviceInfo>query()
                .select("model", "COUNT(*) AS count")
                .eq("mobile", users.getMobile())
                .eq("app_id", appId)
                .apply(true, "DATE_SUB(CURDATE(), INTERVAL " + lastDays + " DAY) <= date(updated_at)")
                .groupBy("model");
        if (!StringUtils.isEmpty(sceneId)) {
            modelQueryWrapper.eq("scene_id", sceneId);
        }
        if (!StringUtils.isEmpty(projectId)) {
            modelQueryWrapper.eq("project_id", projectId);
        }
        List<Map<String, Object>> modelEntities = userDeviceInfoMapper.selectMaps(modelQueryWrapper);

        List<DeviceItemDTO> models = new ArrayList<>();
        modelEntities.forEach(e -> {
            DeviceItemDTO deviceItemDTO = new DeviceItemDTO()
                    .setTotal((long) Integer.parseInt(e.get("count").toString()))
                    .setName(e.get("model").toString());
            models.add(deviceItemDTO);
        });
        userDeviceInfoListDTO.setModels(models);

        QueryWrapper<UserDeviceInfo> manufactureQueryWrapper = Wrappers.<UserDeviceInfo>query()
                .select("manufacture", "COUNT(*) AS count")
                .eq("mobile", users.getMobile())
                .eq("app_id", appId)
                .apply(true, "DATE_SUB(CURDATE(), INTERVAL " + lastDays + " DAY) <= date(updated_at)")
                .groupBy("manufacture");
        if (!StringUtils.isEmpty(sceneId)) {
            manufactureQueryWrapper.eq("scene_id", sceneId);
        }
        if (!StringUtils.isEmpty(projectId)) {
            manufactureQueryWrapper.eq("project_id", projectId);
        }

        List<Map<String, Object>> manufactureEntities = userDeviceInfoMapper.selectMaps(manufactureQueryWrapper);


        List<DeviceItemDTO> manufactures = new ArrayList<>();
        manufactureEntities.forEach(e -> {
            DeviceItemDTO deviceItemDTO = new DeviceItemDTO()
                    .setTotal((long) Integer.parseInt(e.get("count").toString()))
                    .setName(e.get("manufacture").toString());
            manufactures.add(deviceItemDTO);
        });

        userDeviceInfoListDTO.setManufactures(manufactures);
        QueryWrapper<UserDeviceInfo> osVersionQueryWrapper = Wrappers.<UserDeviceInfo>query()
                .select("os_version", "COUNT(*) AS count")
                .eq("mobile", users.getMobile())
                .eq("app_id", appId)
                .apply(true, "DATE_SUB(CURDATE(), INTERVAL " + lastDays + " DAY) <= date(updated_at)")
                .groupBy("os_version");

        if (!StringUtils.isEmpty(sceneId)) {
            osVersionQueryWrapper.eq("scene_id", sceneId);
        }
        if (!StringUtils.isEmpty(projectId)) {
            osVersionQueryWrapper.eq("project_id", projectId);
        }


        List<Map<String, Object>> osVersionEntities = userDeviceInfoMapper.selectMaps(osVersionQueryWrapper);

        List<DeviceItemDTO> osVersions = new ArrayList<>();
        osVersionEntities.forEach(e -> {
            DeviceItemDTO deviceItemDTO = new DeviceItemDTO()
                    .setTotal((long) Integer.parseInt(e.get("count").toString()))
                    .setName(e.get("os_version").toString());
            osVersions.add(deviceItemDTO);
        });
        userDeviceInfoListDTO.setOsVersions(osVersions);

        return userDeviceInfoListDTO;
    }

    @Override
    public void clearUserDeviceInfo(String mobile) {
        baseMapper.delete(Wrappers.<UserDeviceInfo>lambdaQuery().eq(UserDeviceInfo::getMobile, mobile));
    }
}
