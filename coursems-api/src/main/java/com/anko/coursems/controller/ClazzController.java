package com.anko.coursems.controller;

import com.anko.coursems.common.result.ResultCode;
import com.anko.coursems.entity.ClazzMember;
import com.anko.coursems.entity.Notice;
import com.anko.coursems.entity.Resource;
import com.anko.coursems.model.ClazzDetail;
import com.anko.coursems.common.result.Result;
import com.anko.coursems.model.MemberDetail;
import com.anko.coursems.model.ResourceDetail;
import com.anko.coursems.service.impl.ClazzService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/classes")
@RestController
public class ClazzController {
    private Logger log = LoggerFactory.getLogger(ClazzController.class);
    @Autowired
    private ClazzService clazzService;

    @GetMapping("/{id}")
    public Result getClazzDetail(@PathVariable String id) {
        log.info(id);
        ClazzDetail clazzDetail = clazzService.getClazzDetail(id);
        return Result.success(clazzDetail);
    }

    @GetMapping("members/{id}")
    public Result getClazzMembers(@PathVariable String id) {
        log.info(id);
        List<MemberDetail> data = clazzService.getClazzMembers(id);
        return Result.success(data);
    }

    @GetMapping("/notices/{id}")
    public Result getClazzNotices(@PathVariable String id) {
        log.info(id);
        List<Notice> notices = clazzService.getClazzNotices(id);
        return Result.success(notices);
    }

    @GetMapping("/resources/{id}")
    public Result getClazzResources(@PathVariable String id) {
        log.info(id);
        List<ResourceDetail> resources = clazzService.getClazzResources(id);
        return Result.success(resources);
    }

    @DeleteMapping("/members")
    public Result deleteMember(ClazzMember clazzMember) {
        log.info(clazzMember.toString());
        int res = clazzService.deleteMember(clazzMember);
        if( res < 1) {
            return Result.error(ResultCode.INTERFACE_REQUEST_TIMEOUT);
        }
        return Result.success();
    }

    @PostMapping("/notices")
    public Result sendNotice(@RequestBody Notice noticeForm) {
        log.info(noticeForm.toString());
        Notice notice = clazzService.sendNotice(noticeForm);
        return Result.success(notice);
    }

    @DeleteMapping("/notices/{id}")
    public Result deleteNotice(@PathVariable String id) {
        log.info(id);
        int res = clazzService.deleteNotice(id);
        if( res < 1) {
            return Result.error(ResultCode.SYSTEM_INNER_ERROR);
        }
        return Result.success();
    }

    @PostMapping("/resources")
    public Result uploadResource(@RequestBody Resource resourceForm) {
        log.info(resourceForm.toString());
        Resource resource =  clazzService.uploadResource(resourceForm);
        return Result.success(resource);
    }

    @DeleteMapping("/resources/{id}")
    public Result deleteResource(@PathVariable String id) {
        log.info(id);
        int res = clazzService.deleteResource(id);
        if( res < 1) {
            return Result.error(ResultCode.SYSTEM_INNER_ERROR);
        }
        return  Result.success();
    }

    @PutMapping("/grades")
    public Result gradeStudent(@RequestBody ClazzMember scoreForm) {
        log.info(scoreForm.toString());
        int res = clazzService.gradeStudent(scoreForm);
        if( res < 1) {
            return Result.error(ResultCode.SYSTEM_INNER_ERROR);
        }
        return  Result.success();
    }

    @PutMapping("/enableAppraise")
    public Result enableAppraise(@RequestBody ClazzDetail clazzDetail) {
        log.info(clazzDetail.toString());
        int res = clazzService.enableAppraise(clazzDetail);
        if( res < 1) {
            return Result.error(ResultCode.SYSTEM_INNER_ERROR);
        }
        return  Result.success();
    }
}
