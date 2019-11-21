package com.tomcai.cloud.service;

import com.tomcai.cloud.pojo.FileInfo;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.util.List;

public interface FileService {
    boolean upload(MultipartHttpServletRequest request);

    FileInfo getById(String id);

    List<FileInfo> list();

    List<FileInfo> getByTypeId(String typeId);
}
