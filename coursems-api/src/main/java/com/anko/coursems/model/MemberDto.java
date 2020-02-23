package com.anko.coursems.model;

import com.anko.coursems.common.utils.UrlUtils;
import lombok.Data;

@Data
public class MemberDto {
    private String userId;
    private String clazzId;
    private String stuName;
    private String stuNum;
    private String avatar;
    private String signature;
    private int experience;
    private int viewRes;
    private int arrive;
    private int score;
    private String remark;

    public String getAvatar() {
        return UrlUtils.toServerUrl(avatar);
    }
}
