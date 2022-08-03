package com.md.service.service;

import com.md.service.model.dto.RoomUserInfoDTO;
import com.md.service.model.entity.RoomUsers;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 房间用户 服务类
 * </p>
 */
public interface RoomUsersService extends IService<RoomUsers> {

    /**
     *  加入房间 加入合唱 上坐
     * @param roomNo
     * @param userId
     * @param seat 是否上麦 0 未上 1-8 在麦上的位置
     * @param joinSing 是否合唱 0 不合唱 1合唱
     */
    void joinRoom(String roomNo,Integer userId,Integer seat,Integer joinSing);

    /**
     * 校验座位是否有人 是否可以加入合唱
     * @param roomNo
     * @param userId
     * @param seat 是否上麦 0 未上 1-8 在麦上的位置
     * @param joinSing 是否合唱 0 不合唱 1合唱
     * @return
     */
    boolean checkStatus(String roomNo,Integer userId,Integer seat,Integer joinSing);

    /**
     * 来开座位
     * @param roomNo
     * @param userId
     */
    void outSeat(String roomNo,Integer userId);

    /**
     * 获取房间用户信息
     * @param roomNo
     * @return
     */
    List<RoomUserInfoDTO> roomUserInfoDTOList(String roomNo,String masterNo);

    /**
     * 获取房间人数
     * @param roomNo
     * @return
     */
    Long getRoomNum(String roomNo);

    /**
     * 推出房间
     * @param roomNo
     * @param userNo
     */
    void outRoom(String roomNo,String userNo);

    /**
     * 是否开启摄像头
     * @param roomNo
     * @param userNo
     */
    void isVideoMuted(String roomNo,String userNo);

    /**
     * 是否静音
     * @param roomNo
     * @param userNo
     */
    void isSelfMuted(String roomNo,String userNo);
}
