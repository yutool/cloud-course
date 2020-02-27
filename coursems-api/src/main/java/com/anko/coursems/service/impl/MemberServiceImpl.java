package com.anko.coursems.service.impl;

import com.anko.coursems.common.exception.ServiceException;
import com.anko.coursems.dao.MemberMapper;
import com.anko.coursems.entity.Member;
import com.anko.coursems.service.IMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements IMemberService {
    @Autowired
    private MemberMapper memberMapper;

    public void addMember(Member member) {
        if (memberMapper.select(member) != null) {
            throw new ServiceException("成员已存在");
        }
        memberMapper.add(member);
    }

    public int deleteMember(Member member) {
        return memberMapper.delete(member);
    }

    public int gradeStudent(Member scoreForm) {
        return memberMapper.grade(scoreForm);
    }

}
