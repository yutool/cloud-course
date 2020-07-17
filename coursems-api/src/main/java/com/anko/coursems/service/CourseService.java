package com.anko.coursems.service;

import com.anko.coursems.core.IService;
import com.anko.coursems.entity.Course;
import com.anko.coursems.model.CourseDto;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface CourseService extends IService<Course> {

    /**
     * 获取用户所有课程
     * @param id 用户id
     */
    List<Course> getAllCourses(String id);

    /**
     * 获取课程详情 包括课程里的资源等
     * @param id 课程号
     */
    CourseDto getCourseDetail(String id);

    /**
     * 按班号搜索课程
     * @param clazzNum
     */
    Course searchCourse(String clazzNum);

    /**
     * 创建课程
     */
    Course createCourse(Course course);

    /**
     * 删除课程
     */
    boolean deleteCourse(String id);

    /**
     * 开启关闭结课评分功能
     * @param id 课程号
     */
    boolean toggleAppraise(String id);

    /**
     * 上传课程封面
     * @param id 课程号
     * @param file 图片
     * @return 图片地址
     */
    String uploadPic(String id, MultipartFile file);
}
