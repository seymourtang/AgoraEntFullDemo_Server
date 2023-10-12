package com.md.service.exception;

import com.md.service.model.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Configuration
@RestControllerAdvice
@Slf4j
public class BaseGlobalExceptionHandler {

    @ExceptionHandler({BaseException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public BaseResult<?> baseBusinessExceptionHandler(BaseException exception) {
        if (log.isInfoEnabled()) {
            log.info("[BizException]业务异常信息 ex={}", exception.getMessage(), exception);
        }
        return BaseResult.error(exception.getCode(), exception.getMessage());
    }

    @ExceptionHandler({BindException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public BaseResult<?> bindExceptionHandler(BindException exception) {
        if (log.isInfoEnabled()) {
            log.info("[bindException]请求参数错误 ex={}", exception.getMessage(), exception);
        }
        return BaseResult.error(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }

    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public BaseResult<?> httpMessageNotReadableExceptionHandle(HttpMessageNotReadableException exception) {
        if (log.isInfoEnabled()) {
            log.info("[httpMessageNotReadableExceptionHandle]请求参数错误 ex={}", exception.getMessage(), exception);
        }
        return BaseResult.error(HttpStatus.BAD_REQUEST.value(), exception.getMessage());
    }
}
