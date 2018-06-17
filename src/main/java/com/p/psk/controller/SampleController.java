package com.p.psk.controller;

import com.p.psk.entity.User;
import com.p.psk.result.CodeMsg;
import com.p.psk.result.Result;
import com.p.psk.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/seckill")
public class SampleController {

    @Autowired
    UserService userService;

    @RequestMapping("/hello")
    @ResponseBody
    public Result<String> home() {
        return Result.success("Hello,world!");
    }

    @RequestMapping("/error")
    @ResponseBody
    public Result<String> error() {
        return Result.error(CodeMsg.SESSION_ERROR);
    }

    @RequestMapping("/hello/themaleaf")
    public String themaleaf(Model model) {
        model.addAttribute("name", "p");
        return "hello";
    }

    @RequestMapping("/db/get")
    public Result<User> getUserId() {
        User user = userService.getUserId(1);
        return Result.success(user);
    }

    @RequestMapping("/user")
    public String getUserInfo(Model model) {
        model.addAttribute("user", "p");
        return "user";
    }
}
