package com.md.service.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.md.service.model.dto.MetaDataDTO;
import com.md.service.model.entity.MetaData;
import com.md.service.repository.MetaDataMapper;
import com.md.service.service.MetaDataService;
import org.springframework.stereotype.Service;

@Service
public class MetaDataServiceImpl extends ServiceImpl<MetaDataMapper, MetaData> implements MetaDataService {
    @Override
    public MetaDataDTO getMetaData(Integer key) {
        MetaData metaData = baseMapper.selectOne(new QueryWrapper<MetaData>().eq("key", key));
        if (metaData == null) {
            return null;
        }
        return MetaDataDTO.builder()
                .key(metaData.getKey())
                .value(metaData.getValue())
                .createdAt(metaData.getCreatedAt())
                .updatedAt(metaData.getUpdatedAt())
                .build();
    }
}
