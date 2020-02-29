package com.anko.coursems.controller;

import com.anko.coursems.common.annotation.LogAnnotation;
import com.anko.coursems.common.constant.LogType;
import com.anko.coursems.common.utils.FileUrlUtils;
import com.anko.coursems.entity.Course;
import com.anko.coursems.common.result.Result;
import com.anko.coursems.model.CourseDto;
import com.anko.coursems.service.ICourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;

@Api(tags = "课程管理")
@RequestMapping("/api/v1")
@RestController
public class CourseController {
    @Autowired
    private ICourseService courseService;

    @ApiOperation(value = "获取用户所有课程")
    @LogAnnotation(operation = "获取用户所有课程", exclude = {LogType.URL, LogType.RESPONSE})
    @GetMapping("/{userId}/courses")
    public Result getAllCourses(@PathVariable String userId) {
        List<Course> courses = courseService.getAllCourses(userId);
        List<CourseDto> courseList = new LinkedList<>();
        for(Course course : courses)
            courseList.add(new CourseDto().convertFor(course));
        return Result.success(courseList);
    }

    @ApiOperation(value = "按班课搜索课程")
    @LogAnnotation(operation = "按班课搜索课程")
    @GetMapping("/courses/{num}")
    public Result searchCourse(@PathVariable String num) {
        Course course = courseService.searchCourse(num);
        return Result.success(new CourseDto().convertFor(course));
    }

    @ApiOperation(value = "创建课程")
    @LogAnnotation(operation = "创建课程")
    @PostMapping("/courses")
    public Result createCourse(@RequestBody CourseDto form) {
        Course course = courseService.createCourse(form.convertToCourse());
        return Result.success(new CourseDto().convertFor(course));
    }

    @ApiOperation(value = "开启/关闭评分")
    @LogAnnotation(operation = "开启/关闭评分")
    @PutMapping("/courses/appraise/{id}")
    public Result toggleAppraise(@PathVariable String id) {
        boolean appraise = courseService.toggleAppraise(id);
        return  Result.success(appraise);
    }

    @ApiOperation(value = "更换班级图片")
    @LogAnnotation(operation = "更换班级图片")
    @PostMapping("/courses/photo/{id}")
    public Result uploadPic(@PathVariable String id, @RequestParam("file") MultipartFile file) {
        String path = courseService.uploadPic(id, file);
        return Result.success(FileUrlUtils.toServerUrl(path));
    }
}
