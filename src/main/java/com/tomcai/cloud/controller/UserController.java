package com.tomcai.cloud.controller;

import com.tomcai.cloud.pojo.FileInfo;
import com.tomcai.cloud.pojo.User;
import com.tomcai.cloud.service.FileService;
import com.tomcai.cloud.service.UserService;
import com.tomcai.cloud.utils.ShiroUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@Slf4j
public class UserController {
    @Resource
    private UserService userService;

    @RequestMapping("/login")
    public String login() {
        if (!Objects.isNull(SecurityUtils.getSubject().getSession().getAttribute("user"))) {
            return "redirect:/home";
        }
        return "/login";
    }

    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "redirect:/login";
    }

    @PostMapping("/validate")
    public String validate(String username, String password, boolean remember,
                           RedirectAttributes redirectAttributes, HttpServletRequest request) {

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, remember);
        try {
            subject.login(token);
            User user = (User) subject.getPrincipal();
            request.getSession().setAttribute("user", user);
        } catch (Exception e) {
            log.error(e.getMessage());
            redirectAttributes.addFlashAttribute("username", username);
            redirectAttributes.addFlashAttribute("error", "用户名或密码错误");
            return "redirect:/login";
        }

        return "redirect:/home";
    }

    @Resource
    private FileService fileService;

    @RequestMapping({"/home", ""})
    public String home(Model model, HttpServletRequest request) {
        User user = (User) request.getSession().getAttribute("user");
        FileInfo fileInfo = new FileInfo();
        fileInfo.setUsername(user.getUsername());
        model.addAttribute("files", fileService.list(fileInfo));
        return "index";
    }

    @ResponseBody
    @RequestMapping("add")
    public boolean add(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(ShiroUtils.encryption(username, password));
        return userService.add(user);
    }
}
