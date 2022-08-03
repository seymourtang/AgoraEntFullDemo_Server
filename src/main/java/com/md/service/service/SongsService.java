package com.md.service.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.md.service.model.entity.Songs;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 歌曲信息 服务类
 * </p>
 */
public interface SongsService extends IService<Songs> {

    /**
     * 初始化歌曲
     * @param name
     * @param url
     */
    void init(String name ,String url,String no,String imageUrl,String singer);

    /**
     * 查询歌曲
     */
    List<Songs> getList(List<String> songNo,String name);


    /**
     * 根据类型名字分页查询
     * @param page
     * @param name
     * @param type
     * @return
     */
    IPage<Songs> getPage(Page page,String name,String type);
}
