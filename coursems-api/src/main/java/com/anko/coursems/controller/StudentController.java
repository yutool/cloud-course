package com.anko.coursems.controller;

import com.anko.coursems.common.result.Result;
import com.anko.coursems.common.result.ResultCode;
import com.anko.coursems.entity.ClazzMember;
import com.anko.coursems.model.UserCourse;
import com.anko.coursems.service.impl.CourseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RequestMapping("/api/v1/students")
@RestController
public class StudentController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/{userId}/courses")
    public Result getAllCourse(@PathVariable String userId) {
        log.info(userId);
        UserCourse userCourse = courseService.getAllCourse(userId);
        return Result.success(userCourse);
    }

    @PostMapping("/courses")
    public Result joinCourse(@RequestBody ClazzMember joinForm) {
        log.info(joinForm.toString());
        ResultCode code = courseService.joinCourse(joinForm);
        return Result.builder().build().setResultCode(code);
    }

    @DeleteMapping("/courses")
    public Result quitCourse(ClazzMember clazzMember) {
        log.info(clazzMember.toString());
        ResultCode code = courseService.quitCourse(clazzMember);
        return Result.builder().build().setResultCode(code);
    }
}
