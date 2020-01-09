package com.anko.coursems.controller;

import com.anko.coursems.entity.ClazzMember;
import com.anko.coursems.entity.Notice;
import com.anko.coursems.entity.Resource;
import com.anko.coursems.model.ClazzDetail;
import com.anko.coursems.model.RequestResult;
import com.anko.coursems.service.impl.ClazzService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClazzController {
    private Logger log = LoggerFactory.getLogger(ClazzController.class);
    @Autowired
    private ClazzService clazzService;

    @GetMapping("/clazzMembers")
    public RequestResult getClazzMembers(String clazzId) {
        log.info(clazzId);
        return clazzService.getClazzMembers(clazzId);
    }

    @GetMapping("/clazzDetail")
    public RequestResult getClazzDetail(String clazzId) {
        log.info(clazzId);
        return clazzService.getClazzDetail(clazzId);
    }

    @DeleteMapping("/clazzMember")
    public RequestResult deleteMember(ClazzMember clazzMember) {
        log.info(clazzMember.toString());
        return clazzService.deleteMember(clazzMember);
    }

    @GetMapping("/clazzNotices")
    public RequestResult getClazzNotices(String clazzId) {
        log.info(clazzId);
        return clazzService.getClazzNotices(clazzId);
    }

    @PostMapping("/sendNotice")
    public RequestResult sendNotice(@RequestBody Notice noticeForm) {
        log.info(noticeForm.toString());
        return clazzService.sendNotice(noticeForm);
    }

    @DeleteMapping("/deleteNotice")
    public RequestResult deleteNotice(String noticeId) {
        log.info(noticeId);
        return clazzService.deleteNotice(noticeId);
    }

    @GetMapping("/clazzResources")
    public RequestResult getClazzResources(String clazzId) {
        log.info(clazzId);
        return clazzService.getClazzResources(clazzId);
    }

    @PostMapping("/uploadResource")
    public RequestResult uploadResource(@RequestBody Resource resourceForm) {
        log.info(resourceForm.toString());
        return clazzService.uploadResource(resourceForm);
    }

    @DeleteMapping("/deleteResource")
    public RequestResult deleteResource(String resId) {
        log.info(resId);
        return clazzService.deleteResource(resId);
    }

    @PutMapping("/gradeStudent")
    public RequestResult gradeStudent(@RequestBody ClazzMember scoreForm) {
        log.info(scoreForm.toString());
        return clazzService.gradeStudent(scoreForm);
    }

    @PutMapping("/enableAppraise")
    public RequestResult enableAppraise(@RequestBody ClazzDetail clazzDetail) {
        log.info(clazzDetail.toString());
        return clazzService.enableAppraise(clazzDetail);
    }
}
