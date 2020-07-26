package com.ankoye.coursems.model;

import com.ankoye.coursems.common.util.FileUrlUtils;
import com.ankoye.coursems.entity.Member;
import com.google.common.base.Converter;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class MemberDTO {
    private String id;

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

    public MemberDTO convertFor(Member member){
        MemberConverter memberConverter = new MemberConverter();
        MemberDTO memberDto = memberConverter.reverse().convert(member);
        return memberDto;
    }


    private static class MemberConverter extends Converter<MemberDTO, Member> {
        @Override
        protected Member doForward(MemberDTO memberDto) {
            Member member = new Member();
            return member;
        }

        @Override
        protected MemberDTO doBackward(Member member) {
            MemberDTO memberDto = new MemberDTO();
            BeanUtils.copyProperties(member, memberDto);
            return memberDto;
        }

    }
}
