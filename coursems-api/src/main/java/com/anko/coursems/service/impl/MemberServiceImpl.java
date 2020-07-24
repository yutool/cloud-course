package com.anko.coursems.service.impl;

import com.anko.coursems.common.exception.ServiceException;
import com.anko.coursems.common.support.BaseService;
import com.anko.coursems.dao.MemberMapper;
import com.anko.coursems.entity.Member;
import com.anko.coursems.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl extends BaseService<Member> implements MemberService {
    @Autowired
    private MemberMapper memberMapper;

    @Override
    public int addMember(Member member) {
        if (memberMapper.selectOne(member) != null) {
            throw new ServiceException("成员已存在");
        }
        return memberMapper.insert(member);
    }

    @Override
    public Member gradeStudent(Member member) {
        memberMapper.grade(member);
        return memberMapper.selectOne(member);
    }

}
