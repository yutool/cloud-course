package com.anko.coursems.entity;

import com.anko.coursems.common.support.BaseEntity;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * @author ankoye@qq.com
 */
@Data
@Accessors(chain = true)
public class Resource extends BaseEntity {
//    private static final long serialVersionUID = 2560725616980135700L;

    private String resId;

    private String resName;

    private Long resSize;

    private String downLink;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date uploadTime;

    private Integer experience;

    private String courseId;
}
