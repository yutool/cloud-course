package com.anko.coursems.service.impl;

import com.anko.coursems.dao.CourseMapper;
import com.anko.coursems.entity.Course;
import com.anko.coursems.model.CourseDto;
import com.anko.coursems.service.ICourseService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService implements ICourseService {
    @Autowired
    private CourseMapper courseMapper;

    public List<CourseDto> getAllCourses(String userId) {
        return courseMapper.getAllCourses(userId);
    }

    public Course createCourse(Course course) {
        course.setCourseId(RandomStringUtils.randomAlphanumeric(19));
        course.setCourseNum(RandomStringUtils.randomNumeric(6));
        course.setCoursePic("users/upload_avatars/065d41e4-60da-45d1-bfe4-903ed6d80376.png");
        int res = courseMapper.createCourse(course);
        if(res < 1) {
            return null;
        }
        return course;
    }

    public CourseDto searchCourse(String clazzNum) {
        return courseMapper.searchCourse(clazzNum);
    }


    public Course getCourseById(String id) {
        return courseMapper.getCourseById(id);
    }

    public boolean toggleAppraise(Course course) {
        course.setAppraise(!course.isAppraise());
        courseMapper.toggleAppraise(course);
        return course.isAppraise();
    }

    public int savePhoto(String id, String relativePath) {
        return courseMapper.savePhoto(id, relativePath);
    }

}
