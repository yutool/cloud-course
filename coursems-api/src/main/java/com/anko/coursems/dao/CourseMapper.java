package com.anko.coursems.dao;

import com.anko.coursems.entity.Course;
import com.anko.coursems.model.CourseDetail;
import com.anko.coursems.model.CourseDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper {

    List<Course> joinList(String userId);

    List<Course> createList(String userId);

    Course getCourseDetail(String clazzId);

    Course findByNum(String num);

    Course findById(String id);

    int add(Course course);

    int delete(String id);

    int toggleAppraise(Course course);

    int updatePic(@Param("courseId") String id, @Param("coursePic") String relativePath);

}
