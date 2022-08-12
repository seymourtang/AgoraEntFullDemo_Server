package com.md.service.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.md.service.model.dto.RoomOpenUserDTO;
import com.md.service.model.entity.RoomInfo;

import java.util.List;

/**
 * <p>
 * 房间信息 Mapper 接口
 * </p>
 */
public interface RoomInfoMapper extends BaseMapper<RoomInfo> {

    /**
     * 获取开摄像头用户
     * @return
     */
    List<RoomOpenUserDTO> getOpenUser();

    /**
     * 获取所有开麦用户
     * @return
     */
    List<RoomOpenUserDTO> getOpenVoiceUser();
}
