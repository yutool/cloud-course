package com.ankoye.coursems.service.impl;

import com.ankoye.coursems.common.exception.ServiceException;
import com.ankoye.coursems.common.support.BaseService;
import com.ankoye.coursems.dao.MemberMapper;
import com.ankoye.coursems.entity.Member;
import com.ankoye.coursems.service.MemberService;
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
