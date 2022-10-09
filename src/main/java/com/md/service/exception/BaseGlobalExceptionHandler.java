package com.md.service.exception;

import com.md.service.model.BaseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLSyntaxErrorException;

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

    /**
     * 未知的运行时异常
     *
     * @param e 运行时异常
     * @return 失败响应
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public BaseResult<Object> unknown(RuntimeException e) {
        log.error("运行时异常:" + e.getMessage(), e);
        return BaseResult.error("系统运行错误，请联系管理员");
    }


    /**
     * SQL语法异常
     *
     * @param e SQL语法异常
     * @return 失败响应
     */
    @ExceptionHandler(SQLSyntaxErrorException.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public BaseResult<Object> sqlSyntaxError(SQLSyntaxErrorException e) {
        log.error("SQL语法异常:" + e.getMessage(), e);
        return BaseResult.error("数据库错误，请联系管理员");
    }

    /**
     * 系统错误
     *
     * @param e 系统错误
     * @return 失败响应
     */
    @ExceptionHandler(Throwable.class)
    @ResponseBody
    @ResponseStatus(value = HttpStatus.OK)
    public BaseResult<Object> systemError(Throwable e) {
        log.error("服务器错误，请联系管理员", e);
        return BaseResult.error("服务器错误，请联系管理员");
    }

}
