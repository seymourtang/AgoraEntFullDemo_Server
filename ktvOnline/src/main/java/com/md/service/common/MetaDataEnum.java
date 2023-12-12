package com.md.service.common;

import lombok.Getter;

@Getter
public enum MetaDataEnum {
    DiscoverCarouse(1, "发现页面轮播"),
    ;
    private final Integer key;
    private final String desc;

    MetaDataEnum(Integer key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public static MetaDataEnum getEnumValue(Integer integer) {
        for (MetaDataEnum code : values()) {
            if (code.getKey().equals(integer)) {
                return code;
            }
        }
        return null;
    }
}
