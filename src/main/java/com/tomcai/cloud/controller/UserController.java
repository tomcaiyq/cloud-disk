package com.tomcai.cloud.controller;

import com.tomcai.cloud.pojo.FileInfo;
import com.tomcai.cloud.pojo.User;
import com.tomcai.cloud.service.FileService;
import com.tomcai.cloud.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }

    @PostMapping("/validate")
    public String validate(String username, String password, HttpServletRequest request) {
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        subject.login(token);
        request.getSession().setAttribute("user", userService.getByUsername(username));
        return "redirect:home";
    }

    @Resource
    private FileService fileService;

    @RequestMapping("home")
    public String home(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        FileInfo fileInfo = new FileInfo();
        fileInfo.setUploaderId(user.getId());
        model.addAttribute("files", fileService.list(fileInfo));
        return "index";
    }
}