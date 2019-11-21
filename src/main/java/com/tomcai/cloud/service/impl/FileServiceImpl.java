package com.tomcai.cloud.service.impl;

import com.tomcai.cloud.dao.FileDao;
import com.tomcai.cloud.enums.FileTypeEnum;
import com.tomcai.cloud.pojo.FileInfo;
import com.tomcai.cloud.service.FileService;
import com.tomcai.cloud.utils.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    private FileDao fileDao;

    @Value("${file.upload.dir}")
    private String path;

    @Override
    @Transactional
    public boolean upload(MultipartHttpServletRequest request) {
        try {
            Iterator<String> fileNames = request.getFileNames();
            while (fileNames.hasNext()) {
                String fileName = fileNames.next();
                List<MultipartFile> files = request.getFiles(fileName);
                if (files.size() > 0) {
                    files.forEach(f -> {
                        File baseDir = new File(path);
                        File file = new File(baseDir, Objects.requireNonNull(f.getOriginalFilename()));
                        if (!baseDir.exists()) {
                            baseDir.mkdirs();
                        }
                        try {
                            FileInfo fileInfo = new FileInfo();
                            FileTypeEnum typeEnum = FileUtils.fileType(f.getOriginalFilename());
                            fileInfo.setId(UUID.randomUUID().toString());
                            fileInfo.setSize(f.getSize());
                            fileInfo.setUrl(file.getAbsolutePath());
                            fileInfo.setTypeId(typeEnum.getId());
                            fileInfo.setSuffix(typeEnum.getSuffix());
                            fileInfo.setUploaderId("c");
                            fileInfo.setFileName(f.getOriginalFilename());
                            fileDao.insert(fileInfo);
                            f.transferTo(file);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    });
                }
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public FileInfo getById(String id) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(id);
        return fileDao.find(fileInfo);
    }

    @Override
    public List<FileInfo> list() {
        return fileDao.list(new FileInfo());
    }

    @Override
    public List<FileInfo> getByTypeId(String typeId) {
        return null;
    }
}
