package com.anko.coursems.model;

import com.anko.coursems.common.utils.FileUrlUtils;
import com.anko.coursems.entity.Course;
import com.anko.coursems.entity.Member;
import com.anko.coursems.entity.Notice;
import com.anko.coursems.entity.Resource;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.util.LinkedList;
import java.util.List;

@Data
public class CourseDetail {
    private String courseId;

    private String courseNum;

    private String courseName;

    private String coursePic;

    private String clazzName;

    private String synopsis;

    private String term;

    private int arrivesNum;

    private int resourcesNum;

    private int experiencesNum;

    private boolean appraise;

    private String teacherId;

    private String teacherName;

    private List<MemberDto> members;

    private List<ResourceDto> resources;

    private List<NoticeDto> notices;

    public String getCoursePic() {
        return FileUrlUtils.toServerUrl(coursePic);
    }

    public Course convertToCourse(){
        CourseConverter courseConverter = new CourseConverter();
        Course course = courseConverter.convert(this);
        return course;
    }

    public CourseDetail convertFor(Course course){
        CourseConverter courseConverter = new CourseConverter();
        CourseDetail courseDetail = courseConverter.reverse().convert(course);
        return courseDetail;
    }


    private static class CourseConverter extends Converter<CourseDetail, Course> {
        @Override
        protected Course doForward(CourseDetail courseDetail) {
            Course course = new Course();
            // 使用BeanCopier也可以
            BeanUtils.copyProperties(courseDetail, course);
            return course;
        }

        @Override
        protected CourseDetail doBackward(Course course) {
            CourseDetail courseDetail = new CourseDetail();
            BeanUtils.copyProperties(course, courseDetail);
            courseDetail.teacherId = course.getTeacher().getUserId();
            courseDetail.teacherName = course.getTeacher().getUserName();
            // 设置成员
            List<MemberDto> memberDto = new LinkedList<>();
            for(Member m : course.getMembers()) {
                memberDto.add(new MemberDto().convertFor(m));
            }
            courseDetail.setMembers(memberDto);
            // 设置资源
            List<ResourceDto> resourceDto = new LinkedList<>();
            for(Resource r : course.getResources()) {
                resourceDto.add(new ResourceDto().convertFor(r));
            }
            courseDetail.setResources(resourceDto);
            // 设置通知
            List<NoticeDto> noticeDto = new LinkedList<>();
            for(Notice n : course.getNotices()) {
                noticeDto.add(new NoticeDto().convertFor(n));
            }
            courseDetail.setNotices(noticeDto);
            return courseDetail;
        }

    }
}
