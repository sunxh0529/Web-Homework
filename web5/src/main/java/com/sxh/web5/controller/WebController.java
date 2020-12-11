package com.sxh.web5.controller;

import com.sxh.web5.Dao.ContactJpaRepository;
import com.sxh.web5.Data.ContactInfor;
import com.sxh.web5.Data.Table;
import com.sxh.web5.Service.TypeTransformer;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class WebController {

    @Autowired
    private ContactJpaRepository contactJpaRepository;

    // 访问主界面
    @SneakyThrows
    @GetMapping("/main")
    public String showMain(HttpServletRequest request, Model model) {
        Object flag = request.getSession().getAttribute("login");
        if (null != flag) {
            List<ContactInfor> info = contactJpaRepository.findAll();  // 获取持久层的数据
            Table t = new Table(TypeTransformer.listToVector(info));  // 类型转换
            model.addAttribute("table", t);
            return "main";
        }
        else
            return "redirect:/login";
    }
}
