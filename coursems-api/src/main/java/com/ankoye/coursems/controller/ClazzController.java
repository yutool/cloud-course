package com.ankoye.coursems.controller;

import com.ankoye.coursems.common.annotation.LogAnnotation;
import com.ankoye.coursems.common.constant.LogType;
import com.ankoye.coursems.common.support.BaseController;
import com.ankoye.coursems.common.result.Result;
import com.ankoye.coursems.model.CourseDTO;
import com.ankoye.coursems.service.CourseService;
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
        CourseDTO course = courseService.getCourseDetail(id);
        return Result.success(course);
    }

    @ApiOperation(value = "解散班级")
    @LogAnnotation(operation = "解散班级")
    @DeleteMapping("/{id}")
    public Result deleteCourse(@PathVariable String id) {
        return handleResult(courseService.deleteCourse(id));
    }

}
