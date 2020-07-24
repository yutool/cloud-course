package com.anko.coursems.service;

import com.anko.coursems.common.support.IService;
import com.anko.coursems.entity.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ResourceService extends IService<Resource> {

    /**
     * 添加班级资源
     * @param resource 资源信息表
     */
    Resource addResource(Resource resource, MultipartFile file);

}
