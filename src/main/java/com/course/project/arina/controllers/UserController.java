package com.course.project.arina.controllers;

import com.course.project.arina.models.User;
import com.course.project.arina.services.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @PostMapping
    public void add(@RequestBody User user){
        userService.add(user);

    }
    @GetMapping
    public String getAll(Model model){
        model.addAttribute("users",userService.getAll());
        return "users";
    }
    @GetMapping("/{id}")
    public String getById(@PathVariable Long id,Model model){
        model.addAttribute("sum",userService.getSum(id));
        model.addAttribute("user",userService.findById(id));
        model.addAttribute("devices",userService.findById(id).getDevices());
        return "user-info";
    }
    @PostMapping("/{id}")
    public String deleteById(@PathVariable Long id){
        userService.delete(id);
        return "redirect:/users";
    }
    @PostMapping("/edit/{id}")
    public String update(User user, @PathVariable Long id)
    {
        userService.update(id,user);
        return "user-edit";
    }

}
