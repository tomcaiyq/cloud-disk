package com.tomcai.cloud.service.impl;

import com.tomcai.cloud.dao.FileDao;
import com.tomcai.cloud.enums.FileTypeEnum;
import com.tomcai.cloud.pojo.FileInfo;
import com.tomcai.cloud.pojo.User;
import com.tomcai.cloud.service.FileService;
import com.tomcai.cloud.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@Slf4j
public class FileServiceImpl implements FileService {

    @Resource
    private FileDao fileDao;

    @Value("${file.upload.dir}")
    private String path;

    @Value("${file.download.bufferSize}")
    private int bufferSize;

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
                            User user = (User) request.getSession().getAttribute("user");
                            fileInfo.setUploaderId(user.getId());
                            fileInfo.setFileName(f.getOriginalFilename());
                            fileDao.insert(fileInfo);
                            f.transferTo(file);
                            log.info(request.getRemoteAddr() + "上传文件" + f.getOriginalFilename() + "成功!");
                        } catch (IOException e) {
                            log.error(e.getMessage());
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
    public void download(String id, HttpServletResponse response) {
        try {
            FileInfo f = new FileInfo();
            User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
            f.setUploaderId(user.getId());
            f.setId(id);
            FileInfo fileInfo = getById(f);
            File file = new File(fileInfo.getUrl());
            FileInputStream fis = new FileInputStream(fileInfo.getUrl());
            response.reset();
            String fileName = URLEncoder.encode(file.getName(), "UTF-8");
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            byte[] buffer = new byte[bufferSize];
            int temp;
            while ((temp = fis.read(buffer)) != -1) {
                toClient.write(buffer, 0, temp);
            }
            toClient.write(buffer);
            fis.close();
            toClient.flush();
            toClient.close();
        } catch (IOException e) {
            log.error(e.getMessage());
        }
    }

    @Override
    public FileInfo getById(FileInfo fileInfo) {
        return fileDao.find(fileInfo);
    }

    @Override
    public List<FileInfo> list(FileInfo fileInfo) {
        return fileDao.list(fileInfo);
    }

    @Override
    @Transactional
    public int delete(String id) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(id);
        fileInfo.setDel((short) 1);
        //删除硬盘上的文件
       /* FileInfo f = fileDao.find(fileInfo);
        File file = new File(f.getUrl());
        if (file.exists()) {
            file.delete();
        }*/
        return fileDao.delete(fileInfo);
    }
}
