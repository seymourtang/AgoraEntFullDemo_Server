package com.md.service.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.md.service.model.BaseResult;
import com.md.service.model.dto.GetListPageDTO;
import com.md.service.model.entity.Songs;
import com.md.service.service.SongsService;
import com.md.service.utils.AgoraentertainmentUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 歌曲信息 前端控制器
 * </p>
 */
@RestController
@RequestMapping("/songs")
@Slf4j
@Api(tags = "歌曲信息")
public class SongsController {

    @Resource
    private SongsService songsService;

    @Resource
    private AgoraentertainmentUtils getSongs;

    @GetMapping("/getList")
    @ApiOperation("查询歌曲")
    public BaseResult<List<Songs>> chooseSong(@RequestHeader("userNo") String userNo, List<String> songNo, String name) {
        log.info("chooseSong,userNo:{},songNo:{},name:{}", userNo, songNo, name);
        return BaseResult.success(songsService.getList(songNo, name));
    }

    @GetMapping("/getListPage")
    @ApiOperation("查询歌曲")
    public BaseResult<IPage<Songs>> getListPage(@RequestHeader("userNo") String userNo, String name, String type, Page page) {
        log.info("getListPage,userNo:{},name:{}", userNo, name);
        return BaseResult.success(songsService.getPage(page, name, type));
    }

    @PostMapping("/getListPagePost")
    @ApiOperation("查询歌曲")
    public BaseResult<IPage<Songs>> getListPagePost(@RequestHeader("userNo") String userNo, @RequestBody GetListPageDTO form) {
        log.info("getListPagePost,userNo:{},form:{}", userNo, form);
        return BaseResult.success(songsService.getPage(form.getPage(), form.getName(), form.getType()));
    }

    @GetMapping("/getListOnline")
    @ApiOperation("获取歌曲列表")
    public BaseResult<JSONObject> getListOnline(@RequestHeader("userNo") String userNo, Integer pageType, String pageCode, Integer size) {
        log.info("getListOnline userNo:{},pageType:{},pageCode:{},size:{}", userNo, pageType, pageCode, size);
        return BaseResult.success(getSongs.getAll(pageType, pageCode, size));
    }

    @GetMapping("/getSongOnline")
    @ApiOperation("获取歌曲详情")
    public BaseResult<JSONObject> getSongOnline(@RequestHeader("userNo") String userNo, String songCode, Integer lyricType) {
        log.info("getSongOnline,userNo:{},songCode:{},Integer:{}", userNo, songCode, lyricType);
        return BaseResult.success(getSongs.getSong(songCode, lyricType));
    }

    @GetMapping("/getSongOnlineDb")
    @ApiOperation("获取歌曲详情")
    public BaseResult<JSONObject> getSongOnlineDb(@RequestHeader("userNo") String userNo, String songCode, Integer lyricType) {
        log.info("getSong userNo:{},songCode:{},Integer:{}", userNo, songCode, lyricType);
        return BaseResult.success(getSongs.getSongDb(songCode, lyricType));
    }

    @GetMapping("/getSongOnlineAdd")
    @ApiOperation("获取歌曲详情")
    public BaseResult<JSONObject> getSongOnlineAdd(@RequestHeader("userNo") String userNo,Integer pageType, String pageCode, Integer size, String unix) {
        log.info("getSongOnlineAdd userNo:{},pageType:{},pageCode:{},size:{},unix:{}", userNo, pageType, pageCode, size, unix);
        return BaseResult.success(getSongs.getAdd(pageType, pageCode, size, unix));
    }

    @GetMapping("/songHot")
    @ApiOperation("热歌")
    public BaseResult<JSONObject> songHot(@RequestHeader("userNo") String userNo,Integer hotType) {
        log.info("songHot userNo:{},hotType:{}", userNo, hotType);
        return BaseResult.success(getSongs.songHot(hotType));
    }
}
