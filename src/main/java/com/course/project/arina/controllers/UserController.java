package com.course.project.arina.controllers;

import com.course.project.arina.models.User;
import com.course.project.arina.services.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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

    @GetMapping("/edit/{id}")
    public String editUser(@PathVariable Long id, Principal principal, Model model){
        User userToEdit = userService.findById(id);
        model.addAttribute("user", userToEdit);
        return "user-edit";
    }
    @PostMapping("edit/users/editing/{id}")
    public String editingUser(@PathVariable Long id, @RequestParam(name="oldPassword") String oldPassword,
                              @RequestParam(name="password") String password,@RequestParam(name="login") String login, Model model)
    {
        User user = userService.findById(id);
        if(!(password.isEmpty() || oldPassword.isEmpty())) {
            if (userService.doPasswordsMatch(oldPassword, user.getPassword())) {
                user.setPassword(userService.doPasswordEncode(password));
            } else {
                return "redirect:/user/edit/{id}";
            }
        }
        user.setEmail(login);
        userService.update(id, user);
        userService.updatePrincipal(userService.findById(id));
        model.addAttribute("user", userService.findById(id));
        return "main-page";
    }
    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id, @RequestParam(name = "password", required = false)String password){
        if(userService.doPasswordsMatch(password, userService.findById(id).getPassword())){
            userService.delete(id);
            return "registration";
        } else return "redirect:/edit/{id}";
    }

}
