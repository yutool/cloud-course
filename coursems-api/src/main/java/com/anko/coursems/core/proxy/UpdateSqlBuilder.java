package com.anko.coursems.core.proxy;

import com.anko.coursems.core.BaseMapper;
import com.google.common.base.CaseFormat;
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
        final String id = context.getMapperType().getAnnotation(BaseMapper.Meta.class).id();

        StringBuilder sql = new StringBuilder("UPDATE ").append(table).append(" SET ");
        // 设置不为空的字段
        for (Field field : fields) {
            field.setAccessible(true);
            if(field.get(obj) == null) {
                continue;
            }
            // 转换为驼峰
            String fieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
            if (!fieldName.equals(id)) {
                sql.append(fieldName).append(" = #{").append(field.getName()).append("},");
            }
        }
        String ID = CaseFormat.LOWER_UNDERSCORE.to(CaseFormat.LOWER_CAMEL, id);
        sql.replace(sql.length()-1, sql.length(), " WHERE " + id + " = #{" + ID + "};");

        return sql.toString();
    }
}
