package com.md.service.config.delete;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.injector.AbstractSqlInjector;
import com.baomidou.mybatisplus.core.injector.methods.Insert;
import com.baomidou.mybatisplus.core.injector.methods.SelectMaps;
import com.baomidou.mybatisplus.core.metadata.TableInfo;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MdLogicDeleteInjector extends AbstractSqlInjector {

    @Override
    public List<AbstractMethod> getMethodList(Class<?> mapperClass, TableInfo tableInfo) {
        return Stream.of(
                new Insert(),
                new MdLogicDelete(),
                new MdLogicDeleteById(),
                new MdLogicDeleteBatchByIds(),
                new MdLogicUpdate(),
                new MdLogicUpdateById(),
                new MdLogicSelectById(),
                new MdLogicSelectOne(),
                new MdLogicSelectCount(),
                new MdLogicSelectList(),
                new MdLogicSelectPage(),
                new SelectMaps()
        ).collect(Collectors.toList());
    }
}