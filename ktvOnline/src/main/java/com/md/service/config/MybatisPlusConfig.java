package com.md.service.config;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.baomidou.mybatisplus.core.injector.ISqlInjector;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.md.service.config.delete.MdLogicDeleteInjector;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.time.Instant;
import java.time.LocalDateTime;


@Configuration
@EnableTransactionManagement
public class MybatisPlusConfig {


    @Bean
    public ISqlInjector sqlInjector() {
        return new MdLogicDeleteInjector();
    }

    @Bean
    public MetaObjectHandler metaObjectHandler() {
        return new DateFillMetaObjectHandler();
    }

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor(){
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor());
        return interceptor;
    }

    @Slf4j
    public static class DateFillMetaObjectHandler implements MetaObjectHandler {

        /**
         * 插入时填充的字段.
         */
        private static final String INSERT_FIELD = "createdAt";

        /**
         * 插入和更新时都填充的字段.
         */
        private static final String INSERT_UPDATE_FIELD = "updatedAt";

        @Override
        public void insertFill(MetaObject metaObject) {
            LocalDateTime now = LocalDateTime.now();
            Instant instant = Instant.now();
            if (metaObject.hasGetter(INSERT_FIELD) && metaObject.getValue(INSERT_FIELD) == null) {
                Class<?> insertClass = metaObject.getGetterType(INSERT_FIELD);
                if (LocalDateTime.class == insertClass) {
                    setFieldValByName(INSERT_FIELD, now, metaObject);
                } else if (Instant.class == insertClass) {
                    setFieldValByName(INSERT_FIELD, instant, metaObject);
                }
            } else {
                log.info("【DateFillMetaObjectHandler.insertFill】公共字段填充失败,字段不存在或不为空.field={}", INSERT_FIELD);
            }
            if (metaObject.hasGetter(INSERT_UPDATE_FIELD) && metaObject.getValue(INSERT_UPDATE_FIELD) == null) {
                Class<?> insertUpdateClass = metaObject.getGetterType(INSERT_UPDATE_FIELD);
                if (LocalDateTime.class == insertUpdateClass) {
                    setFieldValByName(INSERT_UPDATE_FIELD, now, metaObject);
                } else if (Instant.class == insertUpdateClass) {
                    setFieldValByName(INSERT_UPDATE_FIELD, instant, metaObject);
                }
            } else {
                log.info("【DateFillMetaObjectHandler.insertFill】公共字段填充失败,字段不存在或不为空.field={}", INSERT_UPDATE_FIELD);
            }
        }

        @Override
        public void updateFill(MetaObject metaObject) {
            if (metaObject.hasGetter(INSERT_UPDATE_FIELD)) {
                Class<?> insertUpdateClass = metaObject.getGetterType(INSERT_UPDATE_FIELD);
                if (LocalDateTime.class == insertUpdateClass) {
                    setFieldValByName(INSERT_UPDATE_FIELD, LocalDateTime.now(), metaObject);
                } else if (Instant.class == insertUpdateClass) {
                    setFieldValByName(INSERT_UPDATE_FIELD, Instant.now(), metaObject);
                }
                return;
            }

            // 上面代码不生效，因为insertFill方法和updateFill方法的入参 MetaObject metaObject 中的属性不同 通过BaseMapper找到update方法参数别名@Param("et")
            if (metaObject.hasGetter("et")) {
                Object et = metaObject.getValue("et");
                if (et != null) {
                    MetaObject etMeta = SystemMetaObject.forObject(et);
                    log.debug("updateFill,hasGetter false,et true");
                    if (etMeta.hasSetter(INSERT_UPDATE_FIELD)) {
                        Class<?> insertUpdateClass = etMeta.getGetterType(INSERT_UPDATE_FIELD);
                        if (LocalDateTime.class == insertUpdateClass) {
                            setFieldValByName(INSERT_UPDATE_FIELD, LocalDateTime.now(), etMeta);
                        } else if (Instant.class == insertUpdateClass) {
                            setFieldValByName(INSERT_UPDATE_FIELD, Instant.now(), etMeta);
                        }
                        return;
                    }
                }
            }
            log.info("【DateFillMetaObjectHandler.updateFill】公共字段填充失败,字段不存在或不为空.field={}", INSERT_UPDATE_FIELD);
        }
    }
}