package com.bookstore.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bookstore.entity.TaiKhoan;
import com.bookstore.service.TaiKhoanService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
	@Autowired
	TaiKhoanService tkSer;
    
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }
    @PostMapping("/perform_login")
    public String performLogin(HttpSession session) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	System.out.println(username);
    	session.setAttribute("username", username);
        return "redirect:/home";
    }
    @GetMapping("/home")
    public String showHomePage(Model m) {
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    	String username = authentication.getName();
    	//System.out.println(username);
    	TaiKhoan tk=tkSer.findByUserName(username);
    	//System.out.println(tk.getIsSell());
//    	TaiKhoan tk=new TaiKhoan();
//    	tk=tkSer.findByUserName(username);
        m.addAttribute("tk", tk);
        return "home"; // Ensure you have a "home.html" template
    }
}
