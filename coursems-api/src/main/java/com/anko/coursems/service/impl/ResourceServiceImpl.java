package com.anko.coursems.service.impl;

import com.anko.coursems.common.util.FileContentTypeUtils;
import com.anko.coursems.common.util.FileUtils;
import com.anko.coursems.core.BaseService;
import com.anko.coursems.dao.ResourceMapper;
import com.anko.coursems.entity.Resource;
import com.anko.coursems.service.ResourceService;
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

    @Override
    public int deleteResource(String resId) {
        return resourceMapper.deleteById(resId);
    }
}
