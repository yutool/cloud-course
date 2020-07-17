package com.anko.coursems.core;

import com.anko.coursems.entity.Member;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ankoye@qq.com
 */
public abstract class BaseService<T> implements IService<T>{

    @Autowired
    protected BaseMapper<T> mapper;

    @Override
    public T selectById(Object id) {
        return mapper.selectById(id);
    }

    @Override
    public List<T> selectList(T t) {
        return mapper.selectList(t);
    }

    @Override
    public List<T> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public int save(T t) {
        return mapper.insert(t);
    }

    @Override
    public int updateById(T t) {
        return mapper.updateById(t);
    }

    @Override
    public int deleteById(Object id) {
        return mapper.deleteById(id);
    }

    @Override
    public int delete(T t) {
        return mapper.delete(t);
    }
}
