package com.tomcai.cloud.service;

import com.tomcai.cloud.pojo.FileInfo;

public interface FileService {
    int upload(FileInfo file);

    FileInfo download(String url);
}
