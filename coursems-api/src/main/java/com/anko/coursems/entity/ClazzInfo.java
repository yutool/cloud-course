package com.anko.coursems.entity;

import lombok.Data;
import lombok.ToString;

@ToString(exclude = {"clazzPhoto","clazzExplain","term", "arrives"})
@Data
public class ClazzInfo {
    private String clazzId;
    private String clazzNum;
    private String clazzName;
    private String courseName;
    private String clazzPhoto;
    private String clazzExplain;
    private String term;
    private int arrives;
    private boolean enAppraise;
    private String teaId;
}
