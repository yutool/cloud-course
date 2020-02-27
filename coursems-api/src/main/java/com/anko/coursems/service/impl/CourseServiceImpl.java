package com.anko.coursems.service.impl;

import com.anko.coursems.common.utils.FileUtils;
import com.anko.coursems.dao.CourseMapper;
import com.anko.coursems.dao.MemberMapper;
import com.anko.coursems.dao.NoticeMapper;
import com.anko.coursems.dao.ResourceMapper;
import com.anko.coursems.entity.Course;
import com.anko.coursems.model.CourseDto;
import com.anko.coursems.service.ICourseService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {
    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    private NoticeMapper noticeMapper;
    @Autowired
    private ResourceMapper resourceMapper;

    public List<Course> getAllCourses(String userId) {
        return courseMapper.getAllCourses(userId);
    }

    public Course getCourseById(String id) {
        return courseMapper.getCourseById(id);
    }

    public Course getCourseDetail(String clazzId) {
        return courseMapper.getCourseDetail(clazzId);
    }

    public CourseDto searchCourse(String clazzNum) {
        return courseMapper.searchCourse(clazzNum);
    }

    public Course createCourse(Course course) {
        course.setCourseId(RandomStringUtils.randomAlphanumeric(20));
        course.setCourseNum(RandomStringUtils.randomNumeric(6));
        course.setCoursePic("public/pic_default.jpeg");
        courseMapper.createCourse(course);
        return course;
    }

    @Transactional
    public void deleteCourse(String id) {
        memberMapper.deleteMemberByCourseId(id);
        resourceMapper.deleteResourcesByCourseId(id);
        noticeMapper.deleteNoticeByCourseId(id);
        courseMapper.deleteCourse(id);
    }

    @Transactional
    public boolean toggleAppraise(String id) {
        Course course = courseMapper.getCourseById(id);
        course.setAppraise(!course.isAppraise());
        courseMapper.toggleAppraise(course);
        return course.isAppraise();
    }

    public String uploadPic(String id, MultipartFile file) {
        DateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String fileName = "pic_" + format.format(new Date()) + ".jpeg";
        String path = FileUtils.storeFile(file, fileName, FileUtils.getUserPath(id));
        courseMapper.updatePic(id, path);
        return path;
    }

}
