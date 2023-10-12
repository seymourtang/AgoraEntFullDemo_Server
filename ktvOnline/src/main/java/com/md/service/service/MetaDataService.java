package com.md.service.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.md.service.model.dto.MetaDataDTO;
import com.md.service.model.entity.MetaData;

public interface MetaDataService extends IService<MetaData> {
    /**
     * 获取metaData
     *
     * @param key
     * @return
     */
    MetaDataDTO getMetaData(Integer key);
}
