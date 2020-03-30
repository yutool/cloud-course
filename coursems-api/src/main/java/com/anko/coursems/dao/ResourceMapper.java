package com.anko.coursems.dao;

import com.anko.coursems.entity.Resource;
import org.springframework.stereotype.Repository;

@Repository
public interface ResourceMapper {

    int add(Resource resourceForm);

    int delete(String resId);

    int deleteResourcesByCourseId(String courseId);
}
