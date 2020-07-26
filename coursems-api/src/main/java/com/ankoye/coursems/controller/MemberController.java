package com.ankoye.coursems.controller;

import com.ankoye.coursems.common.annotation.LogAnnotation;
import com.ankoye.coursems.common.result.Result;
import com.ankoye.coursems.common.support.BaseController;
import com.ankoye.coursems.entity.Member;
import com.ankoye.coursems.model.MemberDTO;
import com.ankoye.coursems.service.MemberService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "班级成员管理")
@RestController
@RequestMapping("/api/v1/members")
public class MemberController extends BaseController {
    @Autowired
    private MemberService memberService;

    @ApiOperation(value = "加入课程")
    @LogAnnotation(operation = "加入课程")
    @PostMapping
    public Result addMember(@RequestBody Member form) {
        return handleResult(memberService.addMember(form));
    }

    @ApiOperation(value = "退出课程/删除成员")
    @LogAnnotation(operation = "退出课程/删除成员")
    @DeleteMapping("/{id}")
    public Result deleteMember(@PathVariable Long id) {
        return handleResult(memberService.deleteById(id));
    }

    @ApiOperation(value = "结课评分")
    @LogAnnotation(operation = "结课评分")
    @PutMapping("/grade")
    public Result gradeStudent(@RequestBody MemberDTO form) {
        Member member = memberService.gradeStudent(form.convertToMember());
        return  Result.success(new MemberDTO().convertFor(member));
    }
}
