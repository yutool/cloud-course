package com.anko.coursems.dao;

import com.anko.coursems.entity.ClazzInfo;
import com.anko.coursems.entity.ClazzMember;
import com.anko.coursems.entity.Notice;
import com.anko.coursems.entity.Resource;
import com.anko.coursems.model.ClazzDetail;
import com.anko.coursems.model.MemberDetail;
import com.anko.coursems.model.ResourceDetail;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ClazzMapper {
    // 查询班级成员
    ArrayList<MemberDetail> getClazzMembers(String clazzId);
    // 查询班级详情
    ClazzDetail getClazzDetail(String clazzId);
    // 删除班级成员
    int deleteMember(ClazzMember clazzMember);
    // 查询班级通知
    ArrayList<Notice> getClazzNotices(String clazzId);
    // 发送通知
    int sendNotice(Notice noticeForm);
    // 删除通知
    int deleteNotice(String noticeId);
    // 查询班级资源
    ArrayList<ResourceDetail> getClazzResources(String clazzId);
    // 上传资源
    int uploadResource(Resource resourceForm);
    // 删除资源
    int deleteResource(String resId);
    // 评分
    int gradeStudent(ClazzMember scoreForm);
    // 开启评分
    int enableAppraise(ClazzDetail clazzDetail);
}
