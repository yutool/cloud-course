package com.anko.coursems.core;

import com.anko.coursems.entity.Member;

import java.util.List;

/**
 * @author ankoye@qq.com
 */
public interface IService<T> {

    /**
     * 根据Id查找
     * @param id the id
     * @return the entity
     */
    T selectById(Object id);

    /**
     * 根据存在的属性筛选数据
     * @param t the entity
     * @return the list entity
     */
    List<T> selectList(T t);

    /**
     * 获取表所有的数据
     * @return the list entity
     */
    List<T> selectAll();

    /**
     * 保存数据
     * @param t the entity
     * @return the int
     */
    int save(T t);

    /**
     * 根据Id跟新表数据
     * @param t the entity
     * @return the int
     */
    int updateById(T t);

    /**
     * 根据Id删除表数据
     * @param id the id
     * @return the int
     */
    int deleteById(Object id);

    /**
     * 删除数据
     */
    int delete(T t);
}
