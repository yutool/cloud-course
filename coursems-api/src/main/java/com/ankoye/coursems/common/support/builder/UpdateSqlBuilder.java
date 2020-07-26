package com.ankoye.coursems.common.support.builder;

import com.ankoye.coursems.common.exception.ServiceException;
import com.ankoye.coursems.common.support.BaseMapper;
import com.google.common.base.CaseFormat;
import com.google.common.base.Objects;
import org.apache.commons.lang.StringUtils;
import org.apache.ibatis.builder.annotation.ProviderContext;

import java.lang.reflect.Field;

/**
 * @author ankoye@qq.com
 */
public class UpdateSqlBuilder {

    /**
     * 根据ID跟新数据
     */
    public String buildUpdateById(ProviderContext context, Object obj) throws IllegalAccessException, InstantiationException {
        Field[] fields = obj.getClass().getDeclaredFields();
        final String table = context.getMapperType().getAnnotation(BaseMapper.Meta.class).table();

        StringBuilder sql = new StringBuilder("UPDATE ").append(table).append(" SET ");
        // 设置不为空的字段
        for (Field field : fields) {
            field.setAccessible(true);
            if(field.get(obj) == null) {
                continue;
            }
            // 转换为驼峰
            String fieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
            if (!Objects.equal(fieldName, "id")) {
                sql.append(fieldName).append(" = #{").append(field.getName()).append("},");
            }
        }
        String ID = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, "id");
        sql.replace(sql.length()-1, sql.length(), " WHERE id = #{" + ID + "};");

        return sql.toString();
    }

    /**
     * 根据ID跟新数据
     */
    public String buildUpdateByExId(ProviderContext context, Object obj) throws IllegalAccessException, InstantiationException {
        Field[] fields = obj.getClass().getDeclaredFields();
        final String table = context.getMapperType().getAnnotation(BaseMapper.Meta.class).table();
        final String exId = context.getMapperType().getAnnotation(BaseMapper.Meta.class).exId();
        if(StringUtils.isEmpty(exId)) {
            throw new ServiceException("The extension id cannot be empty");
        }

        StringBuilder sql = new StringBuilder("UPDATE ").append(table).append(" SET ");
        // 设置不为空的字段
        for (Field field : fields) {
            field.setAccessible(true);
            if(field.get(obj) == null) {
                continue;
            }
            // 转换为驼峰
            String fieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
            if (!Objects.equal(fieldName, exId) && !Objects.equal(fieldName, "id")) {
                sql.append(fieldName).append(" = #{").append(field.getName()).append("},");
            }
        }
        String ID = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, exId);
        sql.replace(sql.length()-1, sql.length(), " WHERE " + exId + " = #{" + ID + "};");

        return sql.toString();
    }
}
