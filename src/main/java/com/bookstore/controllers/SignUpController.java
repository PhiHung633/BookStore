package com.bookstore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bookstore.entity.TaiKhoan;
import com.bookstore.service.TaiKhoanService;
import com.bookstore.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/signup")
public class SignUpController {

    @Autowired
    private TaiKhoanService taikhoanser;
    
    
    

//    @PostMapping("/signup")
//    public String signUp(@RequestParam(name = "user") String username, 
//                         @RequestParam(name = "password") String password,
//                         @RequestParam(name = "repeatpassword") String repeatPassword,
//                         @RequestParam(name = "email") String email,
//                         @RequestParam(name = "hovaten") String hovaten,
//                         Model model) {
//        if (!password.equals(repeatPassword)) {
//            model.addAttribute("error", "Passwords do not match.");
//            return "login";
//        }
//
//        TaiKhoan existingTaiKhoan = taikhoanser.checkTaiKhoanOrEmailTonTai(username,email);
//        if (existingTaiKhoan != null) {
//            model.addAttribute("error", "User or Email already exists.");
//            return "login";
//        }
//
//        TaiKhoan taiKhoan = new TaiKhoan();
//        taiKhoan.setUsername(username);
//        taiKhoan.setPassword(password);
//        taiKhoan.setHovaten(hovaten);
//        taiKhoan.setEmail(email);
//        taiKhoan.setIsSell(0);
//        taiKhoan.setIsAdmin(0);
//        taikhoanser.addUser(taiKhoan);
//
//        return "redirect:/login";
//    }
    @ModelAttribute("taikhoan")
    public UserRegistrationDto userRegistrationDto()
    {
    	return new UserRegistrationDto();
    }
    @GetMapping
    public String showSignup()
    {
    	return "signup";
    }
    @PostMapping
    public String registerUserAccount(@ModelAttribute("taikhoan") UserRegistrationDto registrationDto, Model model) {
        if (!registrationDto.getPassword().equals(registrationDto.getRepeatPassword())) {
            model.addAttribute("error", "Passwords do not match.");
            return "signup";
        }

        if (taikhoanser.usernameOrEmailExists(registrationDto.getUsername(), registrationDto.getEmail())) {
            model.addAttribute("error", "Username or Email already exists.");
            return "signup";
        }

        TaiKhoan savedTaiKhoan = taikhoanser.save(registrationDto);
        if (savedTaiKhoan == null) {
            model.addAttribute("error", "There was an error in registration.");
            return "signup";
        }

        return "redirect:/login";
    }
}
