package com.course.project.arina.controllers;

import com.course.project.arina.models.User;
import com.course.project.arina.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class AppController {
    private final UserService userService;
    public User user;

    @GetMapping("/")
    public String main_page(Principal principal, Model model){
        if(userService.getUserByPrincipal(principal).getEmail()!=null){
            model.addAttribute("sum",userService.getSum(user));
            model.addAttribute("devices",userService.getUserByPrincipal(principal).getDevices());
        }
        model.addAttribute("user", userService.getUserByPrincipal(principal));
        return "main-page";
    }
    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/registration")
    public String registration(){ return "registration"; }


    @PostMapping("/registration")
    public String createUser(User user, Model model){
        if(!userService.add(user))
        {
            model.addAttribute("errorMessage","Ошибка регистрации пользователя с почтой: "+user.getEmail());
            return "registration";
        }
        return "redirect:/login";
    }
}
