package com.md.service.model;

import java.util.UUID;

import com.md.service.common.ErrorCodeEnum;
import lombok.Data;

@Data
public class BaseResult<T> {

    private int code = 0;

    private String message = "success";

    private Long ts = System.currentTimeMillis();

    private String requestId = UUID.randomUUID().toString();

    private T data;

    public static BaseResult error(String message) {
        return error(500, message);
    }

    public static BaseResult error(int code, String message) {
        return error(code, message, null);
    }

    public static BaseResult error(ErrorCodeEnum errorCodeEnum, String message) {
        return error(errorCodeEnum.getCode(), message);
    }

    public static <T> BaseResult<T> error(int code, String message, T data) {
        BaseResult<T> result = new BaseResult<>();
        result.setCode(code);
        result.setMessage(message);
        result.setData(data);
        return result;
    }

    public static <T> BaseResult<T> success(T data) {
        BaseResult<T> result = new BaseResult<>();
        result.setData(data);
        return result;
    }

    public static BaseResult success() {
        return success(null);
    }

    public static <T> BaseResult<T> success(String message, T data) {
        BaseResult<T> result = new BaseResult<>();
        result.setMessage(message);
        result.setData(data);
        return result;
    }
}