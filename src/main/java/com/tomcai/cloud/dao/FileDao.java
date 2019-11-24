package com.tomcai.cloud.dao;

import com.tomcai.cloud.pojo.FileInfo;
import org.springframework.stereotype.Repository;

@Repository
public interface FileDao extends BaseDao<FileInfo> {
    FileInfo getByMD5(String md5);

    int insertUserFile(FileInfo fileInfo);

    int isExistFileName(String fileName);
}
