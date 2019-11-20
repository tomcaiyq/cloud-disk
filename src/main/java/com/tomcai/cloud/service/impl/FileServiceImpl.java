package com.tomcai.cloud.service.impl;

import com.tomcai.cloud.dao.FileDao;
import com.tomcai.cloud.pojo.FileInfo;
import com.tomcai.cloud.service.FileService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class FileServiceImpl implements FileService {

    @Resource
    private FileDao fileDao;

    @Override
    public int upload(FileInfo file) {
        return fileDao.insert(file);
    }

    @Override
    public FileInfo download(String url) {
        return null;
    }
}
