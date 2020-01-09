package com.anko.coursems.service.impl;

import com.anko.coursems.dao.ClazzMapper;
import com.anko.coursems.entity.ClazzMember;
import com.anko.coursems.entity.Notice;
import com.anko.coursems.entity.Resource;
import com.anko.coursems.model.*;
import com.anko.coursems.service.IClazzService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ClazzService implements IClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    // 获取班级成员
    public RequestResult getClazzMembers(String clazzId) {
        ArrayList<MemberDetail> list = clazzMapper.getClazzMembers(clazzId);
        return new RequestResult(list,"查询班级成员", StatusCode.SUCCESS);
    }
    // 获取班级详情
    public RequestResult getClazzDetail(String clazzId) {
        ClazzDetail clazzDetail = clazzMapper.getClazzDetail(clazzId);
        return new RequestResult(clazzDetail,"查询班级详情成功", StatusCode.SUCCESS);
    }
    // 删除学生
    public RequestResult deleteMember(ClazzMember clazzMember) {
        int res = clazzMapper.deleteMember(clazzMember);
        if( res >= 1) {
            return new RequestResult("删除班级成员成功", StatusCode.SUCCESS);
        } else {
            return new RequestResult("删除班级成员失败", StatusCode.ERROR);
        }
    }
    // 获取消息
    public RequestResult getClazzNotices(String clazzId) {
        ArrayList<Notice> notices = clazzMapper.getClazzNotices(clazzId);
        return new RequestResult(notices,"查询班级通知成功", StatusCode.SUCCESS);
    }
    // 发送通知
    public RequestResult sendNotice(Notice noticeForm) {
        String noticeId = "n"+ RandomStringUtils.randomAlphanumeric(19);
        noticeForm.setNoticeId(noticeId);
        int res = clazzMapper.sendNotice(noticeForm);
        if( res >= 1) {
            return new RequestResult(noticeForm,"发送通知成功", StatusCode.SUCCESS);
        } else {
            return new RequestResult("发送通知失败", StatusCode.ERROR);
        }
    }
    // 删除通知
    public RequestResult deleteNotice(String noticeId) {
        int res = clazzMapper.deleteNotice(noticeId);
        if( res >= 1) {
            return new RequestResult("删除通知成功", StatusCode.SUCCESS);
        } else {
            return new RequestResult("删除通知失败", StatusCode.ERROR);
        }
    }
    // 获取班级资源
    public RequestResult getClazzResources(String clazzId) {
        ArrayList<ResourceDetail> resources = clazzMapper.getClazzResources(clazzId);
        return new RequestResult(resources,"查询班级资源成功", StatusCode.SUCCESS);
    }
    // 上传资源
    public RequestResult uploadResource(Resource resourceForm) {
        String resId = "r"+ RandomStringUtils.randomAlphanumeric(19);
        resourceForm.setResId(resId);
        int res = clazzMapper.uploadResource(resourceForm);
        if( res >= 1) {
            return new RequestResult(resourceForm,"上传资源成功", StatusCode.SUCCESS);
        } else {
            return new RequestResult("上传资源失败", StatusCode.ERROR);
        }
    }
    // 删除资源
    public RequestResult deleteResource(String resId) {
        int res = clazzMapper.deleteResource(resId);
        if( res >= 1) {
            return new RequestResult("删除资源成功", StatusCode.SUCCESS);
        } else {
            return new RequestResult("删除资源失败", StatusCode.ERROR);
        }
    }
    // 评分
    public RequestResult gradeStudent(ClazzMember scoreForm) {
        int res = clazzMapper.gradeStudent(scoreForm);
        if( res >= 1) {
            return new RequestResult("评分成功", StatusCode.SUCCESS);
        } else {
            return new RequestResult("评分失败", StatusCode.ERROR);
        }
    }
    // 开启评分
    public RequestResult enableAppraise(ClazzDetail clazzDetail) {
        int res = clazzMapper.enableAppraise(clazzDetail);
        if( res >= 1) {
            return new RequestResult("开关结课评分成功", StatusCode.SUCCESS);
        } else {
            return new RequestResult("开关结课评分失败", StatusCode.ERROR);
        }
    }
}
