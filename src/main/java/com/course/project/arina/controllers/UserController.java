package com.course.project.arina.controllers;

import com.course.project.arina.models.Device;
import com.course.project.arina.models.User;
import com.course.project.arina.services.DeviceService;
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
    private final DeviceService deviceService;
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    @PostMapping
    public void add(@RequestBody User user){
        userService.add(user);
    }
    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String getAll(Model model){
        model.addAttribute("users",userService.getAll());
        return "users";
    }
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public String getById(@PathVariable Long id,Model model){
        model.addAttribute("sum",userService.getSum(id));
        model.addAttribute("user",userService.findById(id));
        model.addAttribute("devices",userService.findById(id).getDevices());
        return "user-info";
    }
    @PostMapping("/{id}")
    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    public String deleteById(@PathVariable Long id){
        userService.delete(id);
        return "redirect:/users";
    }
    @PostMapping("/devices")
    public String addDevice( Device device){
        deviceService.add(device);
        return "redirect:/";
    }
   /* @PostMapping("/edit/{id}")
    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    public String update(User user, @PathVariable Long id)
    {
        userService.update(id,user);
        return "user-edit";
    }*/

    @GetMapping("/edit/{id}")
    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    public String editUser(@PathVariable Long id, Principal principal, Model model){
        User userToEdit = userService.findById(id);
        model.addAttribute("user", userToEdit);
        return "user-edit";
    }
    @PostMapping("/editing/{id}")
    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    public String editingUser(@PathVariable Long id, @RequestParam(name="oldPassword") String oldPassword,
                              @RequestParam(name="password") String password,@RequestParam(name="login") String login, Model model)
    {
        User user = userService.findById(id);
        if(!(password.isEmpty() || oldPassword.isEmpty())) {
            if (userService.doPasswordsMatch(oldPassword, user.getPassword())) {
                user.setPassword(userService.doPasswordEncode(password));
            } else {
                return "redirect:/users/edit/{id}";
            }
        }
        user.setEmail(login);
        userService.update(id, user);
        userService.updatePrincipal(userService.findById(id));
        model.addAttribute("user", userService.findById(id));
        return "main-page";
    }
    @PostMapping("/delete/{id}")
    //@PreAuthorize("hasAnyAuthority('ADMIN')")
    public String deleteUser(@PathVariable Long id, @RequestParam(name = "password", required = false)String password){
        if(userService.doPasswordsMatch(password, userService.findById(id).getPassword())){
            userService.delete(id);
            return "registration";
        } else return "error";
    }

}
