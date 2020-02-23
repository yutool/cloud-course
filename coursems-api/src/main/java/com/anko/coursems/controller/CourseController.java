package com.anko.coursems.controller;

import com.anko.coursems.common.result.ResultCode;
import com.anko.coursems.common.utils.FileUploadUtils;
import com.anko.coursems.common.utils.UrlUtils;
import com.anko.coursems.entity.Course;
import com.anko.coursems.common.result.Result;
import com.anko.coursems.model.CourseDto;
import com.anko.coursems.service.impl.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1")
@RestController
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/{userId}/courses")
    public Result getAllCourses(@PathVariable String userId) {
        log.info("获取所有课程: " + userId);
        List<CourseDto> courses = courseService.getAllCourses(userId);
        return Result.success(courses);
    }

    @GetMapping("/courses/{num}")
    public Result searchCourse(@PathVariable String num) {
        log.info("按班号查询课程: " + num);
        CourseDto courseDto = courseService.searchCourse(num);
        if(courseDto == null) {
            return Result.error(ResultCode.RESULE_DATA_NONE);
        }
        return Result.success(courseDto);
    }

    @PostMapping("/courses")
    public Result createCourse(@RequestBody Course form) {
        log.info("创建班级: " + form);
        Course course = courseService.createCourse(form);
        return Result.success(course);
    }

    @PutMapping("/courses/appraise/{id}")
    public Result toggleAppraise(@PathVariable String id) {
        log.info("开启评分: " + id);
        Course course = courseService.getCourseById(id);
        boolean appraise = courseService.toggleAppraise(course);
        return  Result.success(appraise);
    }

    @PostMapping("/courses/photo/{id}")
    public Result uploadPhoto(@PathVariable String id, @RequestParam("file") MultipartFile file) {
        log.info("上传图片: " + id);
        String fileName = FileUploadUtils.storeFile(file, FileUploadUtils.STORE_AVATAR);
        final String relativePath = FileUploadUtils.USER_AVATAR_PR + fileName;
        courseService.savePhoto(id, relativePath);
        return Result.success(UrlUtils.toServerUrl(relativePath));
    }
}
