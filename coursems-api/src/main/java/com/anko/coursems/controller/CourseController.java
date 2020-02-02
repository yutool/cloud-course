package com.anko.coursems.controller;

import com.anko.coursems.common.result.ResultCode;
import com.anko.coursems.entity.ClazzInfo;
import com.anko.coursems.entity.ClazzMember;
import com.anko.coursems.common.result.Result;
import com.anko.coursems.model.ClazzDetail;
import com.anko.coursems.model.UserCourse;
import com.anko.coursems.service.impl.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/v1/courses")
@RestController
public class CourseController {
    private Logger log = LoggerFactory.getLogger(CourseController.class);
    @Autowired
    private CourseService courseService;

    @PostMapping
    public Result createCourse(@RequestBody ClazzInfo clazzForm) {
        log.info(clazzForm.toString());
        ResultCode code = courseService.createCourse(clazzForm);
        return Result.builder().build().setResultCode(code);
    }

    @GetMapping("/{num}")
    public Result searchCourse(@PathVariable String num) {
        log.info(num);
        ClazzDetail clazzDetail = courseService.searchCourse(num);
        if(clazzDetail == null) {
            return Result.error(ResultCode.RESULE_DATA_NONE);
        }
        return Result.success(clazzDetail);
    }

    @DeleteMapping("/{id}")
    public Result dissolveCourse(@PathVariable String id) {
        log.info(id);
        ResultCode code = courseService.dissolveCourse(id);
        return Result.builder().build().setResultCode(code);
    }
}
