package com.md.service.repository;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.md.service.model.entity.UserActionInfo;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * 用户设备信息 Mapper 接口
 * </p>
 */
public interface UserActionInfoMapper extends BaseMapper<UserActionInfo> {
    int insertOrUpdate(@Param("entity") UserActionInfo userActionInfos);
}
