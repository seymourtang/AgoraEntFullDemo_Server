package com.md.service.common;

import lombok.Getter;

@Getter
public enum RoomUserStatus {
    /**
     * 用户
     */
    on_seat(1, "上麦"),
    out_seat(2, "下麦"),

            ;
    private final Integer code;

    private final String message;

    RoomUserStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}

