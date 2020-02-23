package com.anko.coursems.controller;

import com.anko.coursems.common.result.ResultCode;
import com.anko.coursems.common.utils.FileUploadUtils;
import com.anko.coursems.common.utils.JsonUtils;
import com.anko.coursems.entity.Course;
import com.anko.coursems.entity.Member;
import com.anko.coursems.entity.Notice;
import com.anko.coursems.entity.Resource;
import com.anko.coursems.model.ClazzDto;
import com.anko.coursems.common.result.Result;
import com.anko.coursems.model.ResourceDto;
import com.anko.coursems.service.impl.ClazzService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Slf4j
@RequestMapping("/api/v1/classes")
@RestController
/**
 * 班级控制器：包括（成员，资源，通知，评分）
 */
public class ClazzController {
    @Autowired
    private ClazzService clazzService;

    @GetMapping("/{id}")
    public Result getClazz(@PathVariable String id) {
        log.info("获取班级详细信息: " + id);
        ClazzDto clazzDetail = clazzService.getClazzDetail(id);
        return Result.success(clazzDetail);
    }

    @DeleteMapping("/{id}")
    public Result deleteClazz(@PathVariable String id) {
        log.info("解散班级: " + id);
        clazzService.deleteClazz(id);
        return Result.success();
    }

    @PostMapping("/members")
    public Result joinCourse(@RequestBody Member member) {
        log.info("添加成员: " + member);
        ResultCode code = clazzService.addMember(member);
        return Result.builder().build().setResultCode(code);
    }

    @DeleteMapping("/members")
    public Result deleteMember(@RequestBody Member form) {
        log.info("删除成员: " + form);
        int res = clazzService.deleteMember(form);
        if( res < 1) {
            return Result.error(ResultCode.INTERFACE_REQUEST_TIMEOUT);
        }
        return Result.success();
    }

    @PostMapping("/resources")
    public Result uploadResource(@RequestParam("info") String resourceForm , @RequestParam("file") MultipartFile file) {
        Resource resource = JsonUtils.toBean(resourceForm, Resource.class);
        log.info("发布资源: " + resource + file.getOriginalFilename());
        String fileName = FileUploadUtils.storeFile(file, FileUploadUtils.STORE_RESOURCE);
        String downLink = FileUploadUtils.DOWN_LINK_PR + fileName;
        resource.setDownLink(downLink);
        resource.setResSize(String.valueOf(file.getSize()));
        resource.setUploadTime(new Date());
        resource =  clazzService.uploadResource(resource);
        return Result.success(ResourceDto.builder().build().convertFor(resource));
    }

    @DeleteMapping("/resources/{id}")
    public Result deleteResource(@PathVariable String id) {
        log.info("删除资源: " + id);
        int res = clazzService.deleteResource(id);
        if( res < 1) {
            return Result.error(ResultCode.SYSTEM_INNER_ERROR);
        }
        return  Result.success();
    }

    @PostMapping("/notices")
    public Result sendNotice(@RequestBody Notice noticeForm) {
        log.info("发布通知: " + noticeForm);
        Notice notice = clazzService.sendNotice(noticeForm);
        return Result.success(notice);
    }

    @DeleteMapping("/notices/{id}")
    public Result deleteNotice(@PathVariable String id) {
        log.info("删除通知: " + id);
        int res = clazzService.deleteNotice(id);
        if( res < 1) {
            return Result.error(ResultCode.SYSTEM_INNER_ERROR);
        }
        return Result.success();
    }

    @PutMapping("/grades")
    public Result gradeStudent(@RequestBody Member from) {
        log.info("评分: " + from);
        int res = clazzService.gradeStudent(from);
        if( res < 1) {
            return Result.error(ResultCode.SYSTEM_INNER_ERROR);
        }
        return  Result.success(from);
    }

}
