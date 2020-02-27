package com.anko.coursems.service.impl;

import com.anko.coursems.common.utils.FileContentTypeUtils;
import com.anko.coursems.common.utils.FileUtils;
import com.anko.coursems.dao.ResourceMapper;
import com.anko.coursems.entity.Resource;
import com.anko.coursems.service.IResourceService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

@Service
public class ResourceServiceImpl implements IResourceService {

    @Autowired
    private ResourceMapper resourceMapper;

    public Resource addResource(Resource resource, MultipartFile file) {
        String fileName = resource.getResName() + FileContentTypeUtils.getFileType(file.getOriginalFilename());
        String path = FileUtils.storeFile(file, fileName, FileUtils.getUserPath(resource.getCourseId()));
        resource.setDownLink(path);
        resource.setResSize(String.valueOf(file.getSize()));
        resource.setUploadTime(new Date());
        resource.setResId(RandomStringUtils.randomAlphanumeric(20));
        resourceMapper.add(resource);
        return resource;
    }

    public int deleteResource(String resId) {
        return resourceMapper.delete(resId);
    }
}
