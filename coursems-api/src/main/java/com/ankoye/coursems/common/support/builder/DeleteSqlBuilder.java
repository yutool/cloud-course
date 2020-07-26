package com.ankoye.coursems.common.support.builder;

import com.ankoye.coursems.common.exception.ServiceException;
import com.ankoye.coursems.common.support.BaseMapper;
import com.google.common.base.CaseFormat;
import org.apache.commons.lang.StringUtils;
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

        return new SQL(){{
            DELETE_FROM(table);
            WHERE("id = #{id}");
        }}.toString();
    }

    /**
     * 根据扩展ID删除
     */
    public String buildDeleteByExId(ProviderContext context) {
        final String table = context.getMapperType().getAnnotation(BaseMapper.Meta.class).table();
        final String exId = context.getMapperType().getAnnotation(BaseMapper.Meta.class).exId();
        if(StringUtils.isEmpty(exId)) {
            throw new ServiceException("The extension id cannot be empty");
        }

        return new SQL(){{
            DELETE_FROM(table);
            WHERE(exId + " = #{exId}");
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
