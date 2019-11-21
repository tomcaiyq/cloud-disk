package com.tomcai.cloud.controller;

import com.tomcai.cloud.pojo.FileInfo;
import com.tomcai.cloud.service.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.StandardCharsets;

@Controller
@RequestMapping("user")
public class FileController {

    @Value("${file.download.bufferSize}")
    private int bufferSize;

    @Resource
    private FileService fileService;

    @PostMapping("upload")
    public String upload(MultipartHttpServletRequest request) {
        boolean success = fileService.upload(request);
        if (success) return "redirect:/home";
        else return "redirect:/error.html";
    }

    @GetMapping("download")
    public void download(String id, HttpServletResponse response) {
        try {
            FileInfo fileInfo = fileService.getById(id);
            File file = new File(fileInfo.getUrl());
            String filename = file.getName();
            FileInputStream fis = new FileInputStream(fileInfo.getUrl());
            response.reset();
            response.addHeader("Content-Disposition", "attachment;filename=" + new String(filename.getBytes(), StandardCharsets.UTF_8));
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
            e.getMessage();
        }
    }

    @GetMapping("delete")
    public String delete(String id) {
        int delete = fileService.delete(id);
        return "redirect:/home";
    }
}
