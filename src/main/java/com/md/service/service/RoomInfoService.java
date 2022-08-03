package com.md.service.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.md.service.model.dto.RoomInfoDTO;
import com.md.service.model.dto.RoomPageDTO;
import com.md.service.model.entity.RoomInfo;
import com.md.service.model.form.RoomCreateForm;

/**
 * <p>
 * 房间信息 服务类
 * </p>
 */
public interface RoomInfoService extends IService<RoomInfo> {

    /**
     * 创建房间
     * @param form
     */
    String createRoom(RoomCreateForm form);

    /**
     * 关闭房间
     * @param roomNo
     * @param userNo
     */
    void closeRoom(String roomNo,String userNo);

    /**
     * 上麦
     * @param roomNo
     * @param userNo
     */
    void onSeat(String roomNo,String userNo,Integer seat);

    /**
     * 下麦
     * @param roomNo
     * @param userNo
     */
    void outSeat(String roomNo,String userNo);

    /**
     * 修改房间信息
     * @param form
     */
    void updateRoom(RoomCreateForm form);

    /**
     * 获取房间信息
     * @param roomNo
     * @return
     */
    RoomInfoDTO getRooInfo(String roomNo,String userNo,String password);

    /**
     * 推出房间
     * @param roomNo
     * @param userNo
     */
    void outRoom(String roomNo,String userNo);

    /**
     * 房间列表
     * @param page
     * @return
     */
    IPage<RoomPageDTO> roomList(Page page);

    /**
     * 获取房间人数
     * @param roomNo
     * @return
     */
    JSONObject getRoomNum(String roomNo);
}
