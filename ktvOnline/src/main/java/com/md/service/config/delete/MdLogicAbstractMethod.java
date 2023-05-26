package com.md.service.config.delete;

import com.baomidou.mybatisplus.core.injector.AbstractMethod;
import com.baomidou.mybatisplus.core.metadata.TableFieldInfo;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.ExceptionUtils;
import com.baomidou.mybatisplus.core.toolkit.sql.SqlScriptUtils;

public abstract class MdLogicAbstractMethod extends AbstractMethod {

    public MdLogicAbstractMethod() {
        // TO DO
    }


    /**
     * <p>
     * SQL 更新 set 语句
     * </p>
     *
     * @param table
     *            表信息
     * @return sql set 片段
     */
    @Override
    protected String sqlLogicSet(TableInfo table) {
        return "SET " + getLogicDeleteSqlSet(table, false, true);
    }

    /**
     * 重写set逻辑删除字段的 sql
     *
     * @param startWithAnd
     *            是否以 and 开头
     * @param deleteValue
     *            是否需要的是逻辑删除值
     * @return sql 脚本
     */
    public String getLogicDeleteSqlSet(TableInfo table, boolean startWithAnd, boolean deleteValue) {
        if (table.isLogicDelete()) {
            // 判断isLogicDelete是否声明
            TableFieldInfo field =
                table.getFieldList().stream().filter(TableFieldInfo::isLogicDelete).findFirst().orElseThrow(
                    () -> ExceptionUtils.mpe("can't find the logicFiled from table {%s}", table.getTableName()));
            // 获取格式化模板
            String formatStr = field.isCharSequence() ? "'%s'" : "%s";
            String logicDeleteSql = field.getColumn() + EQUALS + String.format(formatStr,
                field.getColumn().contains("flag") ? containsFlagByTime(field) : noContainsFlagByTime(field));
            if (startWithAnd) {
                logicDeleteSql = " AND " + logicDeleteSql;
            }
            return logicDeleteSql;
        }
        return EMPTY;
    }

    /**
     * 获取逻辑删除字段的 sql
     *
     * @param startWithAnd
     *            是否以 and 开头
     * @param deleteValue
     *            是否需要的是逻辑删除值
     * @return sql 脚本
     */
    public String getLogicDeleteSql(TableInfo table, boolean startWithAnd, boolean deleteValue) {
        if (table.isLogicDelete()) {
            // 判断isLogicDelete是否声明
            TableFieldInfo field =
                table.getFieldList().stream().filter(TableFieldInfo::isLogicDelete).findFirst().orElseThrow(
                    () -> ExceptionUtils.mpe("can't find the logicFiled from table {%s}", table.getTableName()));
            // 获取格式化模板
            String formatStr = field.isCharSequence() ? "'%s'" : "%s";
            String logicDeleteSql = field.getColumn() + String.format(formatStr, field.getColumn().contains("flag") ?
                    containsFlag(field) : noContainsFlag(field));
            if (startWithAnd) {
                logicDeleteSql = " AND " + logicDeleteSql;
            }
            return logicDeleteSql;
        }
        return EMPTY;

    }

    /**
     * 判断字段是否包含flag,包含更新时间为unix_timestamp(now())
     * 
     * @param field
     * @return
     */
    private String containsFlagByTime(TableFieldInfo field) {
        return "unix_timestamp(now())";
    }

    /**
     * 判断字段不包含flag,返回is null
     * 
     * @param field
     * @return
     */
    private String noContainsFlagByTime(TableFieldInfo field) {
        return "now()";
    }

    /**
     * 判断字段是否包含flag,返回0
     * 
     * @param field
     * @return
     */
    private String containsFlag(TableFieldInfo field) {
        return "= 0";
    }

    /**
     * 判断字段不包含flag,返回is null
     * 
     * @param field
     * @return
     */
    private String noContainsFlag(TableFieldInfo field) {
        return " is null";
    }

    /**
     * 重写删除逻辑，添加逻辑删除
     */
    @Override
    protected String sqlWhereEntityWrapper(boolean newLine, TableInfo table) {
        if (table.isLogicDelete()) {
            // 获取所有的查询的sql片段
            String sqlScript = table.getAllSqlWhere(true, true, WRAPPER_ENTITY_DOT);
            sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", WRAPPER_ENTITY), true);
            sqlScript += (NEWLINE + getLogicDeleteSql(table, true, false) + NEWLINE);
            String normalSqlScript = SqlScriptUtils.convertIf(String.format("AND ${%s}", WRAPPER_SQLSEGMENT),
                String.format("%s != null and %s != '' and %s", WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT,
                    WRAPPER_NONEMPTYOFNORMAL),
                true);
            normalSqlScript += NEWLINE;
            normalSqlScript += SqlScriptUtils.convertIf(String.format(" ${%s}", WRAPPER_SQLSEGMENT), String.format(
                "%s != null and %s != '' and %s", WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT, WRAPPER_EMPTYOFNORMAL), true);
            sqlScript += normalSqlScript;
            sqlScript = SqlScriptUtils.convertChoose(String.format("%s != null", WRAPPER), sqlScript,
                getLogicDeleteSql(table, false, false));
            sqlScript = SqlScriptUtils.convertWhere(sqlScript);
            return newLine ? NEWLINE + sqlScript : sqlScript;
        } else {
            String sqlScript = table.getAllSqlWhere(false, true, WRAPPER_ENTITY_DOT);
            sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", WRAPPER_ENTITY), true);
            sqlScript += NEWLINE;
            sqlScript += SqlScriptUtils.convertIf(
                String.format(SqlScriptUtils.convertIf(" AND",
                    String.format("%s and %s", WRAPPER_NONEMPTYOFENTITY, WRAPPER_NONEMPTYOFNORMAL), false) + " ${%s}",
                    WRAPPER_SQLSEGMENT),
                String.format("%s != null and %s != '' and %s", WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT,
                    WRAPPER_NONEMPTYOFWHERE),
                true);
            sqlScript = SqlScriptUtils.convertWhere(sqlScript) + NEWLINE;
            sqlScript += SqlScriptUtils.convertIf(String.format(" ${%s}", WRAPPER_SQLSEGMENT), String.format(
                "%s != null and %s != '' and %s", WRAPPER_SQLSEGMENT, WRAPPER_SQLSEGMENT, WRAPPER_EMPTYOFWHERE), true);
            sqlScript = SqlScriptUtils.convertIf(sqlScript, String.format("%s != null", WRAPPER), true);
            return newLine ? NEWLINE + sqlScript : sqlScript;
        }
    }

    @Override
    protected String sqlWhereByMap(TableInfo table) {
        if (table.isLogicDelete()) {
            // 逻辑删除
            String sqlScript = SqlScriptUtils.convertChoose("v == null", " ${k} IS NULL ", " ${k} = #{v} ");
            sqlScript = SqlScriptUtils.convertForeach(sqlScript, "cm", "k", "v", "AND");
            sqlScript = SqlScriptUtils.convertIf(sqlScript, "cm != null and !cm.isEmpty", true);
            sqlScript += (NEWLINE + getLogicDeleteSql(table, true, false));
            sqlScript = SqlScriptUtils.convertWhere(sqlScript);
            return sqlScript;
        } else {
            String sqlScript = SqlScriptUtils.convertChoose("v == null", " ${k} IS NULL ", " ${k} = #{v} ");
            sqlScript = SqlScriptUtils.convertForeach(sqlScript, COLUMN_MAP, "k", "v", "AND");
            sqlScript = SqlScriptUtils.convertWhere(sqlScript);
            sqlScript = SqlScriptUtils.convertIf(sqlScript,
                String.format("%s != null and !%s", COLUMN_MAP, COLUMN_MAP_IS_EMPTY), true);
            return sqlScript;
        }
    }

}
