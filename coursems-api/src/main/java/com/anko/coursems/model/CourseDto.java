package com.anko.coursems.model;

import com.anko.coursems.common.utils.FileUrlUtils;
import com.anko.coursems.entity.Course;
import com.anko.coursems.entity.User;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class CourseDto {
    private String courseId;
    private String courseNum;
    private String courseName;
    private String coursePic;
    private String clazzName;
    private String synopsis;
    private String term;
    private int arrives;
    private int resources;
    private int experiences;
    private boolean appraise;
    private String teacherId;
    private String teacherName;

    // 需要获取服务器绝对地址
    public String getCoursePic() {
        return FileUrlUtils.toServerUrl(coursePic);
    }

    public Course convertToCourse(){
        CourseConverter courseConverter = new CourseConverter();
        Course course = courseConverter.convert(this);
        return course;
    }

    public CourseDto convertFor(Course course){
        CourseConverter courseConverter = new CourseConverter();
        CourseDto courseDto = courseConverter.reverse().convert(course);
        return courseDto;
    }


    private static class CourseConverter extends Converter<CourseDto, Course> {
        @Override
        protected Course doForward(CourseDto courseDto) {
            Course course = new Course();
            User user = new User();
            user.setUserId(courseDto.getTeacherId());
            BeanUtils.copyProperties(courseDto, course);
            course.setTeacher(user);
            return course;
        }

        @Override
        protected CourseDto doBackward(Course course) {
            CourseDto courseDto = new CourseDto();
            BeanUtils.copyProperties(course, courseDto);
            courseDto.teacherId = course.getTeacher().getUserId();
            courseDto.teacherName = course.getTeacher().getUserName();
            return courseDto;
        }

    }
}
