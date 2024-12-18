package com.md.service.common;

import lombok.Getter;

@Getter
public enum UserVerificationStatus {
    /**
     * 用户实名认证
     */
    // 未认证
    NotAuth(0, "未认证"),

    Success(1, "认证成功"),
    ;
    private final Integer code;

    private final String message;

    UserVerificationStatus(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
