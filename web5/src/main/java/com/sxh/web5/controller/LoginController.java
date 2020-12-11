package com.sxh.web5.controller;

import com.sxh.web5.Data.LoginInfor;
import com.sxh.web5.Service.CheckLogin;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

// 登录及相关处理请求控制器
@Controller
public class LoginController {

    // 请求登录界面
    @RequestMapping("/login")
    public String login(LoginInfor user, Model model) {
        model.addAttribute("user", user);
        return "login";
    }

    // 登录检测界面 (登录界面通过post方法发送)
    @SneakyThrows
    @PostMapping("/checklogin")
    public String checkLogin(LoginInfor user, Model model, HttpServletRequest request) {
        if (true == CheckLogin.Check(user)) { // 通过校验
            user.setMessage("OK");
            request.getSession().setAttribute("login", "OK");
            return "redirect:/main";
        } else {// 没通过校验
            user.setMessage("用户名或密码错误");
            user.setPassword("");
            return login(user, model);
        }
    }

    @SneakyThrows
    @GetMapping("/checklogin")
    public String redirectLogin(HttpServletRequest request) {
        if (null == request.getSession().getAttribute("login"))  // 没登录重定向到登录界面
            return "redirect:/login";
        else  // 登录了重定向到主界面
            return "redirect:/main";
    }

}
