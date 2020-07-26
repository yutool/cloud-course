package com.ankoye.coursems.model;

import com.ankoye.coursems.common.util.FileUrlUtils;
import com.ankoye.coursems.entity.Course;
import com.ankoye.coursems.entity.Member;
import com.ankoye.coursems.entity.Notice;
import com.ankoye.coursems.entity.Resource;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.List;

@Data
public class CourseDTO {
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

    public CourseDTO convertFor(Course course){
        CourseConverter courseConverter = new CourseConverter();
        CourseDTO courseDto = courseConverter.reverse().convert(course);
        return courseDto;
    }


    private static class CourseConverter extends Converter<CourseDTO, Course> {
        @Override
        protected Course doForward(CourseDTO courseDto) {
            Course course = new Course();
            BeanUtils.copyProperties(courseDto, course);
            return course;
        }

        @Override
        protected CourseDTO doBackward(Course course) {
            CourseDTO courseDto = new CourseDTO();
            BeanUtils.copyProperties(course, courseDto);
            return courseDto;
        }

    }
}
