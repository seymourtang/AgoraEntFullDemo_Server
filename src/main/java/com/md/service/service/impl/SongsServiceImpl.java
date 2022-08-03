package com.md.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.md.service.model.entity.Songs;
import com.md.service.repository.SongsMapper;
import com.md.service.service.SongsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

/**
 * <p>
 * 歌曲信息 服务实现类
 * </p>
 */
@Service
public class SongsServiceImpl extends ServiceImpl<SongsMapper, Songs> implements SongsService {

    @Override
    public void init(String name, String url,String no,String imageUrl,String singer) {
       List<Songs> lit = baseMapper.selectList(new LambdaQueryWrapper<Songs>().eq(Songs::getSongNo,no));
       if(CollectionUtils.isEmpty(lit)){
           Songs songs = new Songs();
           songs.setSongNo(no);
           songs.setSongName(name);
           songs.setSongUrl(url);
           songs.setImageUrl(imageUrl);
           songs.setSinger(singer);
           songs.setType("");
           baseMapper.insert(songs);
       }
    }

    @Override
    public List<Songs> getList(List<String> songNo,String name) {
        if(!CollectionUtils.isEmpty(songNo)){
           return baseMapper.selectList(new LambdaQueryWrapper<Songs>().in(Songs::getSongNo,songNo));
        }
        if(StringUtils.isNoneBlank(name)){
            return baseMapper.selectList(new LambdaQueryWrapper<Songs>().likeRight(Songs::getSongName,name));
        }
        return Collections.EMPTY_LIST;
    }

    @Override
    public IPage<Songs> getPage(Page page, String name, String type) {
        LambdaQueryWrapper<Songs> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        if(StringUtils.isNoneBlank(name)){
            lambdaQueryWrapper.likeRight(Songs::getSongName,name);
        }
        if(StringUtils.isNoneBlank(type)){
            lambdaQueryWrapper.eq(Songs::getType,type);
        }
        lambdaQueryWrapper.ne(Songs::getLyric,"");
        return baseMapper.selectPage(page,lambdaQueryWrapper);
    }
}
