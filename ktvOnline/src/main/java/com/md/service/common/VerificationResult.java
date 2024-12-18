package com.md.service.common;

import lombok.Getter;

@Getter
public enum VerificationResult {
    /**
     * 实名认证结果
     */
    Success(1, "认证成功"),

    Failed(2, "认证失败"),

            ;
    private final Integer code;

    private final String message;

    VerificationResult(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
