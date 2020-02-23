package com.anko.coursems.dao;

import com.anko.coursems.entity.Course;
import com.anko.coursems.model.CourseDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper {
    // 获取用户加入和创建课程
    List<CourseDto> getAllCourses(String userId);
    // 通过班课号查找课程
    CourseDto searchCourse(String num);
    // 获取课程
    Course getCourseById(String id);
    // 创建课程
    int createCourse(Course course);
    // 删除课程
    int deleteCourse(String id);
    // 开启评分
    int toggleAppraise(Course course);
    // 修改图片
    int savePhoto(@Param("clazzId") String id, @Param("clazzPhoto") String relativePath);
}
