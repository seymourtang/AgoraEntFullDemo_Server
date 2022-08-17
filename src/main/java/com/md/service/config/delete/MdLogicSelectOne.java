package com.md.service.config.delete;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;

import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
/**
 * <p>
 * 根据 ID 删除
 * </p>
 *
 * @author hubin
 * @since 2018-06-13
 */
public class MdLogicSelectOne extends MdLogicAbstractMethod {

    @Override
    public MappedStatement injectMappedStatement(Class<?> mapperClass, Class<?> modelClass, TableInfo tableInfo) {
        SqlMethod sqlMethod = SqlMethod.SELECT_ONE;
        SqlSource sqlSource = languageDriver.createSqlSource(configuration,
            String.format(sqlMethod.getSql(), this.sqlSelectColumns(tableInfo, true)
                    ,tableInfo.getAllSqlSelect(), tableInfo.getTableName(),
                this.sqlWhereEntityWrapper(true, tableInfo), sqlComment()),
            modelClass);
        return this.addSelectMappedStatementForTable(mapperClass, sqlMethod.getMethod(), sqlSource, tableInfo);
    }
}
