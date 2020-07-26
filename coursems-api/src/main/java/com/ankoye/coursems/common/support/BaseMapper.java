package com.ankoye.coursems.common.support;

import com.ankoye.coursems.common.support.builder.DeleteSqlBuilder;
import com.ankoye.coursems.common.support.builder.InsertSqlBuilder;
import com.ankoye.coursems.common.support.builder.SelectSqlBuilder;
import com.ankoye.coursems.common.support.builder.UpdateSqlBuilder;
import org.apache.ibatis.annotations.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

/**
 * 使用时注意：
 * 实体类字段必须为包装类型，否则参数是实体类时可能出现错误
 * 思考优化
 *
 * @author ankoye@qq.com
 */
public interface BaseMapper<T> {

    @SelectProvider(type = SelectSqlBuilder.class, method= "buildSelectById")
    T selectById(Object id);

    @SelectProvider(type = SelectSqlBuilder.class, method= "buildSelectByExId")
    T selectByExId(Object exId);

    @SelectProvider(type = SelectSqlBuilder.class, method= "buildSelectList")
    T selectOne(T t);

    @SelectProvider(type = SelectSqlBuilder.class, method= "buildSelectList")
    List<T> selectList(T t);

    @SelectProvider(type = SelectSqlBuilder.class, method = "buildSelectAll")
    List<T> selectAll();

    @InsertProvider(type = InsertSqlBuilder.class, method = "buildInsert")
    int insert(T t);

    @UpdateProvider(type = UpdateSqlBuilder.class, method = "buildUpdateById")
    int updateById(T t);

    @UpdateProvider(type = UpdateSqlBuilder.class, method = "buildUpdateByExId")
    int updateByExId(T t);

    @DeleteProvider(type = DeleteSqlBuilder.class, method = "buildDelete")
    int delete(T t);

    @DeleteProvider(type = DeleteSqlBuilder.class, method = "buildDeleteByExId")
    int deleteByExId(Object exId);

    @DeleteProvider(type = DeleteSqlBuilder.class, method = "buildDeleteById")
    int deleteById(Object id);


    /**
     * 元注解
     * table - 表名
     * id    - 表id
     */
    @Retention(RetentionPolicy.RUNTIME)
    @Target(ElementType.TYPE)
    @interface Meta {
        String table();
        String exId() default "";
    }
}
