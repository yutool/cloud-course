package com.anko.coursems.service.impl;

import com.anko.coursems.common.result.ResultCode;
import com.anko.coursems.dao.ClazzMapper;
import com.anko.coursems.dao.CourseMapper;
import com.anko.coursems.entity.Member;
import com.anko.coursems.entity.Notice;
import com.anko.coursems.entity.Resource;
import com.anko.coursems.model.*;
import com.anko.coursems.service.IClazzService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClazzService implements IClazzService {
    @Autowired
    private ClazzMapper clazzMapper;
    @Autowired
    private CourseMapper courseMapper;

    public ClazzDto getClazzDetail(String clazzId) {
        return clazzMapper.getClazzDetail(clazzId);
    }

    @Transactional
    public void deleteClazz(String id) {
        clazzMapper.deleteMembers(id);
        clazzMapper.deleteResources(id);
        clazzMapper.deleteNotices(id);
        courseMapper.deleteCourse(id);
    }

    public ResultCode addMember(Member member) {
        if (clazzMapper.findMemberById(member) != null) {
            return ResultCode.DATA_ALREADY_EXISTED;
        }
        int res = clazzMapper.addMember(member);
        if(res < 1) {
            return ResultCode.SYSTEM_INNER_ERROR;
        }
        return ResultCode.SUCCESS;
    }

    public int deleteMember(Member clazzMember) {
        return clazzMapper.deleteMember(clazzMember);
    }

    public Notice sendNotice(Notice noticeForm) {
        String noticeId = "n"+ RandomStringUtils.randomAlphanumeric(19);
        noticeForm.setNoticeId(noticeId);
        clazzMapper.sendNotice(noticeForm);
        return noticeForm;
    }

    public int deleteNotice(String id) {
        return clazzMapper.deleteNotice(id);
    }

    public Resource uploadResource(Resource resourceForm) {
        String resId = "r"+ RandomStringUtils.randomAlphanumeric(19);
        resourceForm.setResId(resId);
        clazzMapper.uploadResource(resourceForm);
        return resourceForm;
    }

    public int deleteResource(String resId) {
        return clazzMapper.deleteResource(resId);
    }

    public int gradeStudent(Member scoreForm) {
        return clazzMapper.gradeStudent(scoreForm);
    }

}
