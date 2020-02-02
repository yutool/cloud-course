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

import java.util.List;

@Service
public class ClazzService implements IClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    // 获取班级成员
    public List<MemberDetail> getClazzMembers(String clazzId) {
        return clazzMapper.getClazzMembers(clazzId);
    }
    // 获取班级详情
    public ClazzDetail getClazzDetail(String clazzId) {
        return clazzMapper.getClazzDetail(clazzId);
    }
    // 删除学生
    public int deleteMember(ClazzMember clazzMember) {
        return clazzMapper.deleteMember(clazzMember);
    }
    // 获取消息
    public List<Notice> getClazzNotices(String clazzId) {
        return clazzMapper.getClazzNotices(clazzId);
    }
    // 发送通知
    public Notice sendNotice(Notice noticeForm) {
        String noticeId = "n"+ RandomStringUtils.randomAlphanumeric(19);
        noticeForm.setNoticeId(noticeId);
        clazzMapper.sendNotice(noticeForm);
        return noticeForm;
    }
    // 删除通知
    public int deleteNotice(String noticeId) {
       return clazzMapper.deleteNotice(noticeId);
    }
    // 获取班级资源
    public List<ResourceDetail> getClazzResources(String clazzId) {
        return clazzMapper.getClazzResources(clazzId);
    }
    // 上传资源
    public Resource uploadResource(Resource resourceForm) {
        String resId = "r"+ RandomStringUtils.randomAlphanumeric(19);
        resourceForm.setResId(resId);
        clazzMapper.uploadResource(resourceForm);
        return resourceForm;
    }
    // 删除资源
    public int deleteResource(String resId) {
        return clazzMapper.deleteResource(resId);
    }
    // 评分
    public int gradeStudent(ClazzMember scoreForm) {
        return clazzMapper.gradeStudent(scoreForm);
    }
    // 开启评分
    public int enableAppraise(ClazzDetail clazzDetail) {
        return clazzMapper.enableAppraise(clazzDetail);
    }
}
