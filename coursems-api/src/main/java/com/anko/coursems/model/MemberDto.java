package com.anko.coursems.model;

import com.anko.coursems.common.utils.FileUrlUtils;
import com.anko.coursems.entity.Member;
import com.anko.coursems.entity.User;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class MemberDto {
    private String userId;
    private String courseId;
    private String stuName;
    private String stuNum;
    private String avatar;
    private String signature;
    private int experience;
    private int resource;
    private int arrive;
    private int score;
    private String remark;

    public String getAvatar() {
        return FileUrlUtils.toServerUrl(avatar);
    }

    public Member convertToMember(){
        MemberConverter memberConverter = new MemberConverter();
        Member member = memberConverter.convert(this);
        return member;
    }

    public MemberDto convertFor(Member member){
        MemberConverter memberConverter = new MemberConverter();
        MemberDto memberDto = memberConverter.reverse().convert(member);
        return memberDto;
    }


    private static class MemberConverter extends Converter<MemberDto, Member> {
        @Override
        protected Member doForward(MemberDto memberDto) {
            Member member = new Member();
            User user = new User();
            user.setUserId(memberDto.getUserId());
            BeanUtils.copyProperties(memberDto, member);
            member.setUser(user);
            return member;
        }

        @Override
        protected MemberDto doBackward(Member member) {
            MemberDto memberDto = new MemberDto();
            BeanUtils.copyProperties(member, memberDto);
            memberDto.setUserId(member.getUser().getUserId());
            memberDto.setStuName(member.getUser().getUserName());
            memberDto.setStuNum(member.getUser().getStuNum());
            memberDto.setAvatar(member.getUser().getAvatar());
            memberDto.setSignature(member.getUser().getSignature());
            return memberDto;
        }

    }
}
