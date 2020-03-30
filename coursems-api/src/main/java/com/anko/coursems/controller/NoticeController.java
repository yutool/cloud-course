package com.anko.coursems.controller;

import com.anko.coursems.common.annotation.LogAnnotation;
import com.anko.coursems.common.result.Result;
import com.anko.coursems.entity.Notice;
import com.anko.coursems.service.INoticeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "班级通知管理")
@RestController
@RequestMapping("/api/v1/notices")
public class NoticeController {
    @Autowired
    private INoticeService noticeService;

    @ApiOperation(value = "添加通知")
    @LogAnnotation(operation = "添加通知")
    @PostMapping
    public Result sendNotice(@RequestBody Notice noticeForm) {
        Notice notice = noticeService.addNotice(noticeForm);
        return Result.success(notice);
    }

    @ApiOperation(value = "删除通知")
    @LogAnnotation(operation = "删除通知")
    @DeleteMapping("/{id}")
    public Result deleteNotice(@PathVariable String id) {
        noticeService.deleteNotice(id);
        return Result.success();
    }
}
