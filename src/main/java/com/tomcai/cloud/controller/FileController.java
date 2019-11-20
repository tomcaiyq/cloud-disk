package com.tomcai.cloud.controller;

import com.tomcai.cloud.pojo.FileInfo;
import com.tomcai.cloud.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

@Controller
@RequestMapping("user")
public class FileController {

    @Resource
    private FileService fileService;

    @PostMapping("upload")
    @ResponseBody
    public String upload(MultipartHttpServletRequest request) {
        return fileService.upload(request);
    }

    @GetMapping("download")
    public void download(String id, HttpServletResponse response) {
        try {
            FileInfo fileInfo = fileService.getById(id);
            File file = new File(fileInfo.getUrl());
            String filename = file.getName();
            InputStream fis = new BufferedInputStream(new FileInputStream(fileInfo.getUrl()));
            byte[] buffer = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            response.reset();
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes()));
            response.addHeader("Content-Length", "" + file.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/octet-stream");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
