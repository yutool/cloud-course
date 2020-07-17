package com.anko.coursems.core.proxy;

import com.anko.coursems.core.BaseMapper;
import com.google.common.base.CaseFormat;
import org.apache.ibatis.builder.annotation.ProviderContext;

import java.lang.reflect.Field;

/**
 * @author ankoye@qq.com
 */
public class InsertSqlBuilder {

    /**
     * 添加数据
     * obj - 添加的对象
     */
    public String buildInsert(ProviderContext context, Object obj) throws IllegalAccessException, InstantiationException {
        final String table = context.getMapperType().getAnnotation(BaseMapper.Meta.class).table();
        Field[] fields = obj.getClass().getDeclaredFields();

        // 生成sql
        StringBuilder sql = new StringBuilder("insert into ").append(table).append("(");
        StringBuilder values = new StringBuilder(" VALUES(");
        // 设置存在的数据
        for (Field field : fields) {
            field.setAccessible(true);
            // 转换为驼峰
            String fieldName = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, field.getName());
            if(field.get(obj) != null) {
                sql.append(fieldName).append(",");
                values.append("#{").append(field.getName()).append("},");
            }
        }
        sql.replace(sql.length() - 1, sql.length(), ") ");
        values.replace(values.length() - 1, values.length(), ");");

        return sql.append(values).toString();
    }
}
