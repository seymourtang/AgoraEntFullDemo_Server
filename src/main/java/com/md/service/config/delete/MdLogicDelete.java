package com.md.service.config.delete;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;

public class MdLogicDelete extends MdLogicAbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        String sql;
        SqlMethod sqlMethod = SqlMethod.LOGIC_DELETE;
        if (tableInfo.isLogicDelete()) {
            sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), sqlLogicSet(tableInfo),
                sqlWhereEntityWrapper(true, tableInfo), sqlComment());
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
            return addUpdateMappedStatement(mapperClass, modelClass, sqlMethod.getMethod(), sqlSource);
        } else {
            sqlMethod = SqlMethod.DELETE;
            sql = String.format(sqlMethod.getSql(), tableInfo.getTableName(), sqlWhereEntityWrapper(true, tableInfo),
                sqlComment());
            SqlSource sqlSource = languageDriver.createSqlSource(configuration, sql, modelClass);
            return this.addDeleteMappedStatement(mapperClass, sqlMethod.getMethod(), sqlSource);
        }
    }
}
