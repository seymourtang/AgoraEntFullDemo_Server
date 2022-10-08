package com.md.service.task;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.md.service.model.dto.RoomOpenUserDTO;
import com.md.service.model.entity.RoomInfo;
import com.md.service.repository.RoomInfoMapper;
import com.md.service.service.RoomInfoService;
import com.md.service.service.RoomUsersService;
import com.md.service.utils.AgoraentertainmentUtils;
import com.md.service.utils.UploadFile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 *
 */
//@Component
@Slf4j
public class ScheduledTaskOpenRecording {

    @Resource
    private RoomInfoMapper roomInfoMapper;

    @Resource
    private RoomUsersService roomUsersService;

    @Resource
    private AgoraentertainmentUtils agoraentertainmentUtils;

    @Resource
    private UploadFile uploadFile;

    @Scheduled(fixedRate = 1000 * 60)
    public void scheduledTaskOpenRecording() {
        log.info("startTimingOpenVideoScreenshots");
        List<RoomOpenUserDTO> list = roomInfoMapper.getOpenUser();
        // 视频截图 并检测
        list.forEach(e -> {
            uploadFile.getImages(e.getRoomNo(),e.getUserId().toString());
        });
        // 检测音频
        List<RoomOpenUserDTO> listVoice = roomInfoMapper.getOpenVoiceUser();
        listVoice.forEach(e -> {
            agoraentertainmentUtils.reviewVoice(e.getUserId(),e.getRoomNo(),e.getOnSeat());
        });
        log.info("openVideoScreenshotsOverTime");
    }
}
