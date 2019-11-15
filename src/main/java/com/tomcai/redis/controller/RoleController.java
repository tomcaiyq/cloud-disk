package com.tomcai.redis.controller;

import com.tomcai.redis.service.RedisService;
import com.tomcai.redis.service.RoleService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping("admin")
public class RoleController {

    @Resource
    private RoleService roleService;

    @GetMapping("list")
    @ResponseBody
    public List<Object> list() {
        return roleService.getList("role:list");
    }

    @GetMapping("page/{page}")
    @ResponseBody
    public List<Object> page(@PathVariable("page") Integer page) {
        Integer pageSize = 100;
        return roleService.list(page, pageSize);
    }
}
