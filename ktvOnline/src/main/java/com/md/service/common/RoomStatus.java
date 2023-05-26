package com.md.service.common;

import lombok.Getter;

@Getter
public enum RoomStatus {
    /**
     * 用户
     */
    OPEN(0, "正常"),
    CLOSE(1, "关闭"),
    ERROR(2, "异常"),
    UPDATE_ROOM_INFO(3, "修改房间信息"),

    ;
    private final Integer code;

    private final String message;

    RoomStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
