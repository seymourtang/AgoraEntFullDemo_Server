package com.md.service.repository;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.md.service.model.entity.MetaData;

public interface MetaDataMapper extends BaseMapper<MetaData> {

    /**
     * 获取metaData
     *
     * @param key
     * @return
     */
    MetaData getMetaData(int key);
}
