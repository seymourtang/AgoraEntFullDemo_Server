package com.md.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.md.service.model.dto.RoomSongInfoDTO;
import com.md.service.model.entity.RoomSong;
import com.md.service.model.form.ChooseSongForm;

import java.util.List;

/**
 * <p>
 * 房间歌曲 服务类
 * </p>
 */
public interface RoomSongService extends IService<RoomSong> {

    /**
     * 点歌
     * @param form
     */
    void chooseSong(ChooseSongForm form);

    /**
     * 切歌
     * @param form
     */
    void switchSong(ChooseSongForm form);

    /**
     * 唱完
     * @param form
     */
    void over(ChooseSongForm form);

    /**
     * 开始唱歌
     * @param form
     */
    void begin(ChooseSongForm form);

    /**
     * 合唱
     * @param form
     */
    void chorus(ChooseSongForm form);

    /**
     * 取消合唱
     * @param form
     */
    void cancelChorus(ChooseSongForm form);

    /**
     * 删除合唱者
     * @param roomNo
     * @param userNo
     */
    void delChorus(String roomNo,String userNo);

    /**
     * 获取房间歌曲信息
     * @param roomNo
     * @return
     */
    List<RoomSongInfoDTO> getRoomSongInfo(String roomNo);

    /**
     * 已点列表
     * @param roomNo
     */
    List<RoomSongInfoDTO> haveOrderedList(String roomNo);

    /**
     * 置顶
     * @param roomNo
     * @param songNo
     */
    void toDevelop(String roomNo,String songNo,Integer sort);

    /**
     * 删除
     * @param roomNo
     * @param songNo
     */
    void delSong(String roomNo,String songNo,Integer sort);

    /**
     * 删除 此房间所有点歌曲
     * @param roomNo
     * @param userNo
     */
    void delSong(String roomNo,String userNo);
}
