package com.md.service.config.delete;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.scripting.defaults.RawSqlSource;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;

public class MdLogicSelectById extends MdLogicAbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.SELECT_BY_ID;
        SqlSource sqlSource = new RawSqlSource(configuration,
            String.format(sqlMethod.getSql(), sqlSelectColumns(tableInfo, false), tableInfo.getTableName(),
                tableInfo.getKeyColumn(), tableInfo.getKeyProperty(), getLogicDeleteSql(tableInfo, true, false)),
            Object.class);
        return this.addSelectMappedStatementForTable(mapperClass, sqlMethod.getMethod(), sqlSource, tableInfo);
    }
}
