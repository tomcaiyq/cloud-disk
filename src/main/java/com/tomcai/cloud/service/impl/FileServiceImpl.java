package com.tomcai.cloud.service.impl;

import com.tomcai.cloud.dao.FileDao;
import com.tomcai.cloud.enums.FileTypeEnum;
import com.tomcai.cloud.pojo.FileInfo;
import com.tomcai.cloud.pojo.User;
import com.tomcai.cloud.service.FileService;
import com.tomcai.cloud.utils.FileUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

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
    @Transactional(propagation = Propagation.REQUIRED)
    public boolean upload(MultipartHttpServletRequest request) {
        try {
            Iterator<String> fileNames = request.getFileNames();
            final User user = (User) request.getSession().getAttribute("user");
            while (fileNames.hasNext()) {
                String fileName = fileNames.next();
                List<MultipartFile> files = request.getFiles(fileName);
                if (files.size() > 0) {
                    for (MultipartFile file : files) {
                        boolean flag = false;
                        String md5 = null;
                        try {
                            md5 = DigestUtils.md5DigestAsHex(file.getBytes());
                            if (fileDao.getByMD5(md5) != null) {
                                flag = true;
                            }
                        } catch (IOException e) {
                            log.error(e.getMessage());
                        }

                        String dirName = FileUtils.dirName();
                        File baseDir = new File(path + dirName);

                        if (!baseDir.exists()) {
                            baseDir.mkdirs();
                        }
                        FileTypeEnum typeEnum = FileUtils.fileType(Objects.requireNonNull(file.getOriginalFilename()));

                        String fName = FileUtils.fileName(typeEnum.getSuffix());
                        File path = new File(baseDir, fName);

                        FileInfo fileInfo = new FileInfo();
                        fileInfo.setSize(file.getSize());
                        fileInfo.setUsername(user.getUsername());
                        fileInfo.setSizeShow(FileUtils.fileSize(file.getSize()));
                        fileInfo.setUrl(path.getAbsolutePath());
                        fileInfo.setUserUrl(dirName + "/" + fName);
                        fileInfo.setTypeId(typeEnum.getId());
                        fileInfo.setSuffix(typeEnum.getSuffix());
                        fileInfo.setFileName(fName);
                        fileInfo.setMd5(md5);
                        if (validateFileName(file.getOriginalFilename()) > 0)
                            fileInfo.setUserFileName(FileUtils.fileName(file.getOriginalFilename(), typeEnum.getSuffix()));
                        else
                            fileInfo.setUserFileName(file.getOriginalFilename());
                        if (!flag) {
                            try {
                                insertFile(fileInfo);
                                file.transferTo(path);
                            } catch (IOException e) {
                                log.error(e.getMessage());
                            }
                        }
                        insertUserFile(fileInfo.setId(fileDao.getByMD5(md5).getId()));
                        log.info(request.getRemoteAddr() + "上传文件" + file.getOriginalFilename() + "成功!");
                    }
                }
            }
            return true;
        } catch (Exception e) {
            log.error(e.getMessage());
            return false;
        }
    }

    @Override
    public void download(Long id, HttpServletResponse response) {
        try {
            FileInfo fileInfo = getById(new FileInfo().setId(id));
            File file = new File(fileInfo.getUrl());
            FileInputStream fis = new FileInputStream(fileInfo.getUrl());
            response.reset();
            String fileName = URLEncoder.encode(fileInfo.getUserFileName(), "UTF-8");
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
    public int delete(Long id) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(id);
        fileInfo.setDel((short) 1);
        return fileDao.delete(fileInfo);
    }

    @Override
    @Transactional
    public int insertFile(FileInfo fileInfo) {
        return fileDao.insert(fileInfo);
    }

    @Override
    @Transactional
    public int insertUserFile(FileInfo fileInfo) {
        return fileDao.insertUserFile(fileInfo);
    }

    @Override
    public int validateFileName(String fileName) {
        return fileDao.isExistFileName(fileName);
    }
}
