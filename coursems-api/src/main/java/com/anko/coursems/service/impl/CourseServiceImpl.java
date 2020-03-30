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

    @Transactional
    public List<Course> getAllCourses(String userId) {
        List<Course> joinList = courseMapper.joinList(userId);
        joinList.addAll(courseMapper.createList(userId));
        return joinList;
    }

    public Course getCourseById(String id) {
        return courseMapper.findById(id);
    }

    public Course getCourseDetail(String clazzId) {
        return courseMapper.getCourseDetail(clazzId);
    }

    public Course searchCourse(String clazzNum) {
        return courseMapper.findByNum(clazzNum);
    }

    public Course createCourse(Course course) {
        course.setCourseId(RandomStringUtils.randomAlphanumeric(20));
        course.setCourseNum(RandomStringUtils.randomNumeric(6));
        course.setCoursePic("public/pic_default.jpeg");
        courseMapper.add(course);
        return course;
    }

    @Transactional
    public void deleteCourse(String id) {
        memberMapper.deleteMembersByCourseId(id);
        resourceMapper.deleteResourcesByCourseId(id);
        noticeMapper.deleteNoticesByCourseId(id);
        courseMapper.delete(id);
    }

    @Transactional
    public boolean toggleAppraise(String id) {
        Course course = courseMapper.findById(id);
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
