package com.tomcai.cloud.controller;

import com.tomcai.cloud.service.FileService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
public class IndexController {

    @Resource
    private FileService fileService;

    @RequestMapping(value = {"index.html", ""})
    public String index(Model model) {
        model.addAttribute("files", fileService.list());
        return "index";
    }
}
