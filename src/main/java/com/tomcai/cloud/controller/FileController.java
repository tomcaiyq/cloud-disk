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
import java.net.URLEncoder;

@Controller
@RequestMapping("user")
public class FileController {

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
        fileService.download(id, response);
    }

    @GetMapping("delete")
    public String delete(String id) {
        fileService.delete(id);
        return "redirect:/home";
    }
}
