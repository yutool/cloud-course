package com.anko.coursems.controller;

import com.anko.coursems.entity.ClazzInfo;
import com.anko.coursems.entity.ClazzMember;
import com.anko.coursems.model.RequestResult;
import com.anko.coursems.service.impl.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CourseController {
    private Logger log = LoggerFactory.getLogger(CourseController.class);
    @Autowired
    private CourseService courseService;

    @GetMapping("/allCourse")
    public RequestResult getAllCourse(String userId) {
        log.info(userId);
        return courseService.getAllCourse(userId);
    }

    @PostMapping("/createCourse")
    public RequestResult createCourse(@RequestBody ClazzInfo clazzForm) {
        log.info(clazzForm.toString());
        return courseService.createCourse(clazzForm);
    }

    @GetMapping("/searchCourse")
    public RequestResult searchCourse(String clazzNum) {
        log.info(clazzNum);
        return courseService.searchCourse(clazzNum);
    }

    @PostMapping("/joinCourse")
    public RequestResult joinCourse(@RequestBody ClazzMember joinForm) {
        log.info(joinForm.toString());
        return  courseService.joinCourse(joinForm);
    }

    @DeleteMapping("quitCourse")
    public RequestResult quitCourse(ClazzMember clazzMember) {
        log.info(clazzMember.toString());
        return courseService.quitCourse(clazzMember);
    }

    @DeleteMapping("dissolveCourse")
    public RequestResult dissolveCourse(String clazzId) {
        log.info(clazzId);
        return courseService.dissolveCourse(clazzId);
    }


}
