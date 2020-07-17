package com.anko.coursems.model;

import com.anko.coursems.common.utils.FileUrlUtils;
import com.anko.coursems.entity.*;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class CourseDto {
    private String courseId;

    private String courseNum;

    private String courseName;

    private String coursePic;

    private String clazzName;

    private String synopsis;

    private String term;

    private Integer arrivesNum;

    private Integer resourcesNum;

    private Integer experiencesNum;

    private boolean appraise;

    private String teacherId;

    private String teacherName;

    private List<Member> members;

    private List<Resource> resources;

    private List<Notice> notices;

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
            BeanUtils.copyProperties(courseDto, course);
            return course;
        }

        @Override
        protected CourseDto doBackward(Course course) {
            CourseDto courseDto = new CourseDto();
            BeanUtils.copyProperties(course, courseDto);
            return courseDto;
        }

    }
}
