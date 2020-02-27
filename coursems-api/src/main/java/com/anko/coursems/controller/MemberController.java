package com.anko.coursems.controller;

import com.anko.coursems.common.annotation.LogAnnotation;
import com.anko.coursems.common.result.Result;
import com.anko.coursems.model.MemberDto;
import com.anko.coursems.service.IMemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "班级成员管理")
@RestController
@RequestMapping("/api/v1/members")
public class MemberController {
    @Autowired
    private IMemberService memberService;

    @ApiOperation(value = "加入课程")
    @LogAnnotation(operation = "加入课程")
    @PostMapping
    public Result addMember(@RequestBody MemberDto form) {
        memberService.addMember(form.convertToMember());
        return Result.success();
    }

    @ApiOperation(value = "退出课程/删除成员")
    @LogAnnotation(operation = "退出课程/删除成员")
    @DeleteMapping
    public Result deleteMember(@RequestBody MemberDto form) {
        memberService.deleteMember(form.convertToMember());
        return Result.success();
    }

    @ApiOperation(value = "结课评分")
    @LogAnnotation(operation = "结课评分")
    @PutMapping("/grades")
    public Result gradeStudent(@RequestBody MemberDto form) {
        memberService.gradeStudent(form.convertToMember());
        return  Result.success();
    }
}
