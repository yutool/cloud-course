package com.anko.coursems.core.proxy;

import com.anko.coursems.core.BaseMapper;
import com.google.common.base.CaseFormat;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;

/**
 * @author ankoye@qq.com
 */
public class SelectSqlBuilder {

    /**
     * 根据ID查找
     */
    public String buildSelectById(ProviderContext context) {
        // 获取接口上的元注解（不是实体）
        final String table = context.getMapperType().getAnnotation(BaseMapper.Meta.class).table();
        final String id = context.getMapperType().getAnnotation(BaseMapper.Meta.class).id();

        return new SQL(){{
            SELECT("*");
            FROM(table);
            WHERE(id + " = #{id}");
        }}.toString();
    }

    /**
     * 根据实体类查找
     */
    public String buildSelectList(ProviderContext context, Object obj) throws IllegalAccessException {
        final String table = context.getMapperType().getAnnotation(BaseMapper.Meta.class).table();
        Field[] fields = obj.getClass().getDeclaredFields();

        StringBuilder sql = new StringBuilder("SELECT * FROM ").append(table).append(" WHERE ");
        // 设置不为空的字段
        for (Field field : fields) {
            field.setAccessible(true);
            if(field.get(obj) != null) {
                // 转换为驼峰
                String fieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
                sql.append(fieldName).append(" = #{").append(field.getName()).append("} AND ");
            }
        }
        sql.replace(sql.length() - 5, sql.length(), ";");

        return sql.toString();
    }

    /**
     * 查找所有数据
     */
    public String buildSelectAll(ProviderContext context) {
        final String table = context.getMapperType().getAnnotation(BaseMapper.Meta.class).table();

        return new SQL(){{
            SELECT("*");
            FROM(table);
        }}.toString();
    }
}
