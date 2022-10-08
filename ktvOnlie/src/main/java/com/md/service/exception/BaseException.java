package com.md.service.exception;

import com.md.service.common.ErrorCodeEnum;
import lombok.Data;

@Data
public class BaseException extends RuntimeException {

    private Integer code;
    private Object data;


    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
    }

    public BaseException(ErrorCodeEnum errorCodeEnum, String message) {
        super(message);
        this.code = errorCodeEnum.getCode();

    }

    public BaseException(ErrorCodeEnum errorCodeEnum, String message, Object data) {
        super(message);
        this.code = errorCodeEnum.getCode();
        this.data = data;

    }

    public BaseException(Integer code, String message, Object data) {
        super(message);
        this.code = code;
        this.data = data;
    }

    public BaseException(String message) {
        super(message);
        this.code = 500000;
    }

    public BaseException(ErrorCodeEnum errorEnum) {
        super(errorEnum.getMessage());
        this.code = errorEnum.getCode();
    }

    public BaseException(ErrorCodeEnum errorEnum, Object message) {
        super(errorEnum.getMessage());
        this.code = errorEnum.getCode();
        this.data = message;
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
        this.code = 500000;
    }


    /**
     * 提升性能
     *
     * @return Throwable
     */
    @Override
    public Throwable fillInStackTrace() {
        return this;
    }
}
