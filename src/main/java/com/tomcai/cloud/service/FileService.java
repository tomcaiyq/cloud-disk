package com.tomcai.cloud.service;

import com.tomcai.cloud.pojo.FileInfo;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface FileService {
    boolean upload(MultipartHttpServletRequest request);

    FileInfo getById(FileInfo fileInfo);

    List<FileInfo> list(FileInfo fileInfo);

    int delete(String id);

    void download(String id, HttpServletResponse response);
}
