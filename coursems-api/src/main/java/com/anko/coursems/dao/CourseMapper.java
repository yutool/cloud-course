package com.anko.coursems.dao;

import com.anko.coursems.entity.Course;
import com.anko.coursems.model.CourseDetail;
import com.anko.coursems.model.CourseDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseMapper {

    List<Course> getAllCourses(String userId);

    Course getCourseDetail(String clazzId);

    CourseDto searchCourse(String num);

    Course getCourseById(String id);

    int createCourse(Course course);

    int toggleAppraise(Course course);

    int updatePic(@Param("courseId") String id, @Param("coursePic") String relativePath);

    int deleteCourse(String id);
}
