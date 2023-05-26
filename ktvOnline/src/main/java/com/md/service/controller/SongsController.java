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
    public BaseResult<List<Songs> > chooseSong(List<String> songNo,String name){
        return BaseResult.success(songsService.getList(songNo,name));
    }

    @GetMapping("/getListPage")
    @ApiOperation("查询歌曲")
    public BaseResult<IPage<Songs>> getListPage(String name, String type, Page page){
        log.info("getListPage chooseSong  , name : {}",name);
        return BaseResult.success(songsService.getPage(page,name,type));
    }

    @PostMapping("/getListPagePost")
    @ApiOperation("查询歌曲")
    public BaseResult<IPage<Songs>> getListPagePost(@RequestBody GetListPageDTO form){
        log.info("chooseSongPost chooseSong form , form : {}",form);
        return BaseResult.success(songsService.getPage(form.getPage(),form.getName(),form.getType()));
    }

    @GetMapping("/getListOnline")
    @ApiOperation("获取歌曲列表")
    public BaseResult<JSONObject> getListOnline(Integer pageType, String pageCode, Integer size){
        log.info("getList pageType : {} , pageCode : {} , size:{}",pageType,pageCode,size);
        return BaseResult.success(getSongs.getAll(pageType,pageCode,size));
//        return BaseResult.success();
    }

    @GetMapping("/getSongOnline")
    @ApiOperation("获取歌曲详情")
    public BaseResult<JSONObject> getSongOnline(String songCode, Integer lyricType){
        log.info(" getSong songCode : {} , Integer : {} ",songCode,lyricType);
        return BaseResult.success(getSongs.getSong(songCode,lyricType));
    }

    @GetMapping("/getSongOnlineDb")
    @ApiOperation("获取歌曲详情")
    public BaseResult<JSONObject> getSongOnlineDb(String songCode, Integer lyricType){
        log.info(" getSong songCode : {} , Integer : {} ",songCode,lyricType);
        return BaseResult.success(getSongs.getSongDb(songCode,lyricType));
    }

    @GetMapping("/getSongOnlineAdd")
    @ApiOperation("获取歌曲详情")
    public BaseResult<JSONObject> getSongOnlineAdd(Integer pageType, String pageCode, Integer size,String unix){
        return BaseResult.success(getSongs.getAdd(pageType,pageCode,size,unix));
    }

    @GetMapping("/songHot")
    @ApiOperation("热歌")
    public BaseResult<JSONObject> songHot(Integer hotType){
        log.info("songHot hotType : {} ",hotType);
        return BaseResult.success(getSongs.songHot(hotType));
//        return BaseResult.success();
    }
}
