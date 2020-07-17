package com.anko.coursems.controller;

import com.anko.coursems.common.annotation.LogAnnotation;
import com.anko.coursems.common.constant.LogType;
import com.anko.coursems.core.BaseController;
import com.anko.coursems.common.result.Result;
import com.anko.coursems.model.CourseDto;
import com.anko.coursems.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "班级管理")
@RequestMapping("/api/v1/classes")
@RestController
public class ClazzController extends BaseController {
    @Autowired
    private CourseService courseService;

    @ApiOperation(value = "获取班级详情")
    @LogAnnotation(operation = "获取班级详情", exclude = {LogType.URL})
    @GetMapping("/{id}")
    public Result getCourseDetail(@PathVariable String id) {
        CourseDto course = courseService.getCourseDetail(id);
        return Result.success(course);
    }

    @ApiOperation(value = "解散班级")
    @LogAnnotation(operation = "解散班级")
    @DeleteMapping("/{id}")
    public Result deleteCourse(@PathVariable String id) {
        return handleResult(courseService.deleteCourse(id));
    }

}
