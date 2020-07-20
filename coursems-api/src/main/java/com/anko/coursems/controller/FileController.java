package com.anko.coursems.controller;

import com.anko.coursems.common.annotation.LogAnnotation;
import com.anko.coursems.common.constant.LogType;
import com.anko.coursems.common.util.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Slf4j
@Api(tags = "文件下载")
@RestController
@RequestMapping("/files")
public class FileController {

    // @GetMapping("/download/{path:.+}/{filename:.+}") // 匹配两层路径
    @GetMapping("/download/**") // 匹配多重路径
    @ApiOperation(value = "下载文件")
    @LogAnnotation(operation = "下载文件", exclude = {LogType.REQUEST, LogType.RESPONSE})
    public ResponseEntity<Resource> downloadFile(HttpServletRequest request) {
        // substring("/file/download/".length())
        String resourcePath = request.getServletPath().substring(16);
        // Load file as Resource
        Resource resource = FileUtils.loadFileAsResource(resourcePath);
        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            log.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
                .body(resource);
    }
}
