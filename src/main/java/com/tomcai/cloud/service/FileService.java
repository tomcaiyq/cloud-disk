package com.tomcai.cloud.service;

import com.tomcai.cloud.pojo.FileInfo;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

public interface FileService {
    boolean upload(MultipartHttpServletRequest request);

    FileInfo getById(FileInfo fileInfo);

    List<FileInfo> list(FileInfo fileInfo);

    int delete(Long id);

    void download(Long id, HttpServletResponse response);

    int insertFile(FileInfo fileInfo);

    int insertUserFile(FileInfo fileInfo);

    int validateFileName(String fileName);

    List<FileInfo> recycleList(String username);

    int restore(FileInfo fileInfo);
}
