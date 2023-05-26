package com.md.service.exception;

import com.md.service.model.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
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
        return  BaseResult.error(exception.getCode(), exception.getMessage());
    }
}
