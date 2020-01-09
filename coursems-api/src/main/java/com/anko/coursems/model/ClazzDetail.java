package com.anko.coursems.model;

import com.anko.coursems.entity.ClazzMember;
import com.anko.coursems.entity.Notice;
import lombok.Data;

import java.util.List;

@Data
public class ClazzDetail {
    private String clazzId;
    private String clazzNum;
    private String clazzName;
    private String courseName;
    private String clazzPhoto;
    private String clazzExplain;
    private String term;
    private int arriveNum;
    private int resourceNum;
    private int experience;
    private String teaId;
    private String teaName;
    private boolean enAppraise;
    private List<ClazzMember> members;
    private List<ResourceDetail> resources;
    private List<Notice> notices;
}
