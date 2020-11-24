package com.sxh.demo.controller;

import com.sxh.demo.Data.BookList;
import com.sxh.demo.Data.ConInfo;
import com.sxh.demo.Data.LoginInfo;
import com.sxh.demo.models.CheckLogin;
import com.sxh.demo.models.ListEdit;
import lombok.SneakyThrows;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class MainController {
    @RequestMapping("/login")
    public String login(LoginInfo user, Model model) {
        model.addAttribute("user", user);
        return "login";
    }

    @SneakyThrows
    @PostMapping("/checklogin")
    public String logincheck(LoginInfo user, Model model, HttpServletRequest request) {
        if (true == CheckLogin.Check(user)) {
            user.setMessage("success");
            request.getSession().setAttribute("login", "yes");
            return "redirect:/list";
        }
        else {
            user.setMessage("登录信息错误");
            user.setPassword("");
            return login(user, model);
        }
    }

    @SneakyThrows
    @GetMapping("/checklogin")
    public String redirectlogin(HttpServletRequest request) {
        if (null == request.getSession().getAttribute("login"))
            return "redirect:/login";
        else
            return "redirect:/list";
    }

    @SneakyThrows
    @GetMapping("/list")
    public String showList(HttpServletRequest request) {
        Object f = request.getSession();
        if(null != f) {
            HttpSession session = request.getSession();
            if(null == session.getAttribute("list")) {
                BookList bookList = new BookList();
                session.setAttribute("list", bookList);
            }
            return "list";
        }
        else
            return "redirect:/login";
    }

    @RequestMapping("/addNew")
    public String showAddNew(ConInfo conInfo, Model model) {
        model.addAttribute("conInfo", conInfo);
        return "addNew";
    }

    @GetMapping("/checkadd")
    public String redirectAdd() {
        return "addNew";
    }

    @PostMapping("/checkadd")
    public String showAddNew(ConInfo conInfo, HttpServletRequest request, Model model) {
        BookList bookList = (BookList)request.getSession().getAttribute("list");
        boolean isValid = ListEdit.checkValid(bookList, conInfo);
        if(true == isValid) {
            bookList.getBookinfo().addElement(conInfo);
            return "redirect:/list";
        }
        else {
            conInfo.setMsg("该联系人已存在");
            conInfo.setName("");
            return showAddNew(conInfo, model);
        }
    }

    @PostMapping("/edit")
    public String showEdit(HttpServletRequest request, @ModelAttribute(value = "row")Integer row, Model model) {
        BookList bookList = (BookList)request.getSession().getAttribute("list");
        ConInfo conInfo = bookList.getBookinfo().elementAt(row);
        model.addAttribute("conInfo", conInfo);
        return "edit";
    }

    @GetMapping("/edit")
    public String redirectEdit() {
        return "redirect:/list";
    }

    @GetMapping("/checkedit")
    public String redirectCheckEdit() {
        return "redirect:/list";
    }

    @PostMapping("/checkedit")
    public String checkEdit(ConInfo conInfo, HttpServletRequest request) {
        BookList bookList = (BookList)request.getSession().getAttribute("list");
        ListEdit.editElem(bookList, conInfo);
        return "redirect:/list";
    }

    @GetMapping("/del")
    public String redirectDel() {
        return "redirect:/list";
    }

    @PostMapping("/del")
    public String DeleteContact(@ModelAttribute(value = "row")Integer row, HttpServletRequest request) {
        BookList bookList = (BookList)request.getSession().getAttribute("list");
        ListEdit.delElem(bookList, row);
        return "redirect:/list";
    }
}
