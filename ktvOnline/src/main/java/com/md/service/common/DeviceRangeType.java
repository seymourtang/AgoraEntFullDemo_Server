package com.md.service.common;

import lombok.Getter;

import java.util.Objects;

@Getter
public enum DeviceRangeType {
    Last7Days(0, 7),
    Last1Month(1, 30),
    Last1Year(2, 365),
    ;
    private final Integer type;

    private final Integer lastDays;

    DeviceRangeType(Integer type, Integer lastDays) {
        this.type = type;
        this.lastDays = lastDays;
    }

    public static DeviceRangeType getEnumValue(Integer integer) {
        for (DeviceRangeType code : values()) {
            if (Objects.equals(code.getType(), integer)) {
                return code;
            }
        }
        return null;
    }
}
