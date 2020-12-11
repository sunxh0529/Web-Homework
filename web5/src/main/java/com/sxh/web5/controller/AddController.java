package com.sxh.web5.controller;

import com.sxh.web5.Dao.ContactJpaRepository;
import com.sxh.web5.Data.ContactInfor;
import com.sxh.web5.Data.Table;
import com.sxh.web5.Service.TableAlters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class AddController {

    // JPA
    @Autowired
    private ContactJpaRepository contactJpaRepository;

    // 必须要先登录
    @RequestMapping("/add")
    public String showAdd(ContactInfor cont, Model model, HttpServletRequest request) {
        if (null == request.getSession().getAttribute("login"))
            return "redirect:/login";
        else {
            model.addAttribute("cont", cont);
            return "add";
        }
    }

    @GetMapping("/checkadd")
    public String redirectAdd() {
        return "redirect:/add";  // 返回模型redirect:/....  springboot 会自动创建或获取相关Mapping 方法所需的参数
    }

    @PostMapping("/checkadd")
    public String checkAdd(ContactInfor cont, HttpServletRequest request, Model model) {
        List<ContactInfor> list = contactJpaRepository.findByContactname(cont.getContactname());
        if (0 == list.size()) {
            contactJpaRepository.save(cont);
            return "redirect:/main";
        } else {
            cont.setMessage("联系人名称已存在");
            cont.setContactname("");
            return showAdd(cont, model, request);
        }
    }

    @ResponseBody
    @PostMapping("/checktel")
    public int checkTel(@RequestParam(name="tel")String tel, HttpServletRequest request) {
        Table table = (Table)request.getSession().getAttribute("table");
        boolean result = TableAlters.hasTel(contactJpaRepository.findAll(), tel);
        if (result)
            return 1;
        else
            return 0;
    }
}
