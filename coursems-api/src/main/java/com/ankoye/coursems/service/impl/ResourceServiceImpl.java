package com.ankoye.coursems.service.impl;

import com.ankoye.coursems.common.util.FileContentTypeUtils;
import com.ankoye.coursems.common.util.FileUtils;
import com.ankoye.coursems.common.support.BaseService;
import com.ankoye.coursems.dao.ResourceMapper;
import com.ankoye.coursems.entity.Resource;
import com.ankoye.coursems.service.ResourceService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class ResourceServiceImpl extends BaseService<Resource> implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    @Override
    public Resource addResource(Resource resource, MultipartFile file) {
        String fileName = resource.getResName() + FileContentTypeUtils.getFileType(file.getOriginalFilename());
        String path = FileUtils.storeFile(file, fileName, FileUtils.getUserPath(resource.getCourseId()));
        resource.setResId(RandomStringUtils.randomAlphanumeric(20));
        resource.setDownLink(path);
        resource.setResSize(file.getSize());
        resource.setUploadTime(new Date());
        resourceMapper.insert(resource);
        return resource;
    }

}
