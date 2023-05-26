package com.md.service.repository;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.md.service.model.entity.UserConfig;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户设备信息 Mapper 接口
 * </p>
 */
public interface UserConfigMapper extends BaseMapper<UserConfig> {
    int insertOrUpdate(@Param("entity") UserConfig userConfig);
}
