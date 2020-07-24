package com.anko.coursems.common.support;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @author ankoye@qq.com
 */
public abstract class BaseService<T> implements IService<T>{

    @Autowired
    protected BaseMapper<T> mapper;

    @Override
    public T selectById(Long id) {
        return mapper.selectById(id);
    }

    @Override
    public T selectByExId(Object exId) {
        return mapper.selectByExId(exId);
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
    public int updateByExId(T t) {
        return mapper.updateByExId(t);
    }

    @Override
    public int deleteById(Long id) {
        return mapper.deleteById(id);
    }

    @Override
    public int deleteByExId(Object exId) {
        return mapper.deleteByExId(exId);
    }

    @Override
    public int delete(T t) {
        return mapper.delete(t);
    }
}
