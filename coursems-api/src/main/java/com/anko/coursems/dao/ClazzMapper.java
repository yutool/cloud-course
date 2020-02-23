package com.anko.coursems.dao;

import com.anko.coursems.entity.Member;
import com.anko.coursems.entity.Notice;
import com.anko.coursems.entity.Resource;
import com.anko.coursems.model.ClazzDto;
import org.springframework.stereotype.Repository;

@Repository
public interface ClazzMapper {
    // 查询班级详情
    ClazzDto getClazzDetail(String clazzId);
    // 添加成员
    int addMember(Member member);
    // 删除班级成员
    int deleteMember(Member clazzMember);
    // 删除班级成员
    int deleteMembers(String id);
    // 上传资源
    int uploadResource(Resource resourceForm);
    // 删除资源
    int deleteResource(String resId);
    // 删除资源
    int deleteResources(String id);
    // 发送通知
    int sendNotice(Notice noticeForm);
    // 删除通知
    int deleteNotice(String noticeId);
    // 删除通知
    int deleteNotices(String id);
    // 评分
    int gradeStudent(Member scoreForm);
    // 查看成员是否在班级中
    Member findMemberById(Member member);
}
