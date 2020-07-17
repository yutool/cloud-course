package com.anko.coursems.core.proxy;

import com.anko.coursems.core.BaseMapper;
import com.google.common.base.CaseFormat;
import org.apache.ibatis.builder.annotation.ProviderContext;
import org.apache.ibatis.jdbc.SQL;

import java.lang.reflect.Field;

/**
 * @author ankoye@qq.com
 */
public class DeleteSqlBuilder {

    /**
     * 根据ID删除
     */
    public String buildDeleteById(ProviderContext context) {
        final String table = context.getMapperType().getAnnotation(BaseMapper.Meta.class).table();
        final String id = context.getMapperType().getAnnotation(BaseMapper.Meta.class).id();

        return new SQL(){{
            DELETE_FROM(table);
            WHERE(id + " = #{id}");
        }}.toString();
    }

    /**
     * 删除数据
     */
    public String buildDelete(ProviderContext context, Object obj) throws IllegalAccessException {
        final String table = context.getMapperType().getAnnotation(BaseMapper.Meta.class).table();

        StringBuilder sql = new StringBuilder("DELETE FROM ").append(table).append(" WHERE ");
        Field[] fields = obj.getClass().getDeclaredFields();
        // 设置存在的数据
        for (Field field : fields) {
            field.setAccessible(true);
            if (field.get(obj) != null) {
                String fieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
                sql.append(fieldName).append(" = #{").append(field.getName()).append("} AND ");
            }
        }
        sql.replace(sql.length()-5, sql.length(), ";");
        return sql.toString();
    }
}
