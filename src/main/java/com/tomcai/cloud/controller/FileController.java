package com.tomcai.cloud.controller;

import com.tomcai.cloud.common.ResultBean;
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
import java.util.List;

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
    public void download(Long id, HttpServletResponse response) {
        fileService.download(id, response);
    }

    @GetMapping("delete")
    public String delete(Long id) {
        fileService.delete(id);
        return "redirect:/home";
    }

    @GetMapping("recycle")
    @ResponseBody
    public ResultBean<List<FileInfo>> recycle(String username) {
        return new ResultBean<>(fileService.recycleList(username));
    }

    @GetMapping("restore")
    @ResponseBody
    public ResultBean restore(Long id) {
        FileInfo fileInfo = new FileInfo();
        fileInfo.setId(id);
        if (fileService.restore(fileInfo) > 0)
            return new ResultBean();
        else return new ResultBean("fail");
    }
}
