package com.md.service.controller;

import com.md.service.model.BaseResult;
import com.md.service.model.dto.RoomSongInfoDTO;
import com.md.service.model.form.ChooseSongForm;
import com.md.service.service.RoomSongService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 房间歌曲 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/roomSong")
@Slf4j
@Api(tags = "房间歌曲")
public class RoomSongController {

    @Resource
    private RoomSongService roomSongService;

    @GetMapping("/chooseSong")
    @ApiOperation("点歌")
    public BaseResult<String> chooseSong(@RequestHeader("userNo") String userNo, ChooseSongForm form) {
        form.setUserNo(userNo);
        log.info("chooseSong form : {}", form);
        roomSongService.chooseSong(form);
        return BaseResult.success();
    }

    @GetMapping("/switchSong")
    @ApiOperation("切歌")
    public BaseResult<String> switchSong(@RequestHeader("userNo") String userNo, ChooseSongForm form) {
        form.setUserNo(userNo);
        log.info("switchSong form : {}", form);
        roomSongService.switchSong(form);
        return BaseResult.success();
    }

    @GetMapping("/over")
    @ApiOperation("唱完")
    public BaseResult<String> over(@RequestHeader("userNo") String userNo, ChooseSongForm form) {
        form.setUserNo(userNo);
        log.info("over form : {}", form);
        roomSongService.over(form);
        return BaseResult.success();
    }

    @GetMapping("/begin")
    @ApiOperation("开始唱歌")
    public BaseResult<String> begin(@RequestHeader("userNo") String userNo, ChooseSongForm form) {
        form.setUserNo(userNo);
        log.info("begin form : {}", form);
        roomSongService.begin(form);
        return BaseResult.success();
    }

    @GetMapping("/chorus")
    @ApiOperation("加入合唱")
    public BaseResult<String> chorus(@RequestHeader("userNo") String userNo, ChooseSongForm form) {
        form.setUserNo(userNo);
        log.info("chorus form : {}", form);
        roomSongService.chorus(form);
        return BaseResult.success();
    }

    @GetMapping("/cancelChorus")
    @ApiOperation("取消合唱")
    public BaseResult<String> cancelChorus(@RequestHeader("userNo") String userNo, ChooseSongForm form) {
        form.setUserNo(userNo);
        log.info("cancelChorus form : {}", form);
        roomSongService.cancelChorus(form);
        return BaseResult.success();
    }


    @GetMapping("/haveOrderedList")
    @ApiOperation("已点列表")
    public BaseResult<List<RoomSongInfoDTO>> haveOrderedList(@RequestHeader("userNo") String userNo, String roomNo) {
        log.info("haveOrderedList roomNo : {} ,userNo:{}", roomNo, userNo);
        return BaseResult.success(roomSongService.haveOrderedList(roomNo));
    }

    @GetMapping("/toDevelop")
    @ApiOperation("置顶歌曲")
    public BaseResult<?> toDevelop(@RequestHeader("userNo") String userNo, String roomNo, String songNo, Integer sort) {
        log.info("toDevelop roomNo : {},userNo:{}", roomNo, userNo);
        roomSongService.toDevelop(roomNo, songNo, sort);
        return BaseResult.success();
    }

    @GetMapping("/delSong")
    @ApiOperation("删除歌曲")
    public BaseResult<?> delSong(@RequestHeader("userNo") String userNo, String roomNo, String songNo, Integer sort) {
        log.info("haveOrderedList roomNo : {},userNo:{}", roomNo, userNo);
        roomSongService.delSong(roomNo, songNo, sort);
        return BaseResult.success();
    }
}
