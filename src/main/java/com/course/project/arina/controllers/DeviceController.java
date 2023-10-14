package com.course.project.arina.controllers;

import com.course.project.arina.enums.Status;
import com.course.project.arina.models.Device;
import com.course.project.arina.services.DeviceService;
import com.course.project.arina.services.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('USER', 'ADMIN')")
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService deviceService;
    private final UserService userService;
    @GetMapping
    public String getAll(@RequestParam(name = "status",required = false)Status status, Model model,@RequestParam(required = false) Boolean sortedByDate,@RequestParam(required = false) Boolean sortedByStatus){


        if(sortedByDate==null) sortedByDate=false;
        if (sortedByStatus==null) sortedByStatus=false;
//        if(sortedByDate) sortedByStatus=false;
//        if (sortedByStatus) sortedByDate=false;
        System.err.println(sortedByStatus);
        System.err.println(sortedByDate);
        if(sortedByDate) {model.addAttribute("devices", deviceService.sortByDate());}
        else if(sortedByStatus) {model.addAttribute("devices", deviceService.sortByStatus());}
        else {model.addAttribute("devices",deviceService.getAll(status));}
        model.addAttribute("users",userService.getAll());
        model.addAttribute("statuses", Arrays.stream(Status.values()).collect(Collectors.toList()));
        model.addAttribute("sortedByDate", sortedByDate);
        model.addAttribute("sortedByStatus", sortedByStatus);
        //если админ то функционал такой, если юзер то вызываем юзер.гетДевайсис
        return "devices";
    }
    @GetMapping("/{id}")
    public String getById(@PathVariable Long id,Model model){
        model.addAttribute("device",deviceService.findById(id));
        return "device-info";
    }
    @PostMapping
    public String add(Device device){
        deviceService.add(device);
        return "redirect:/devices";

    }
    @PostMapping("/{id}")
    public String deleteById(@PathVariable Long id){
        deviceService.delete(id);
        return "redirect:/devices";
    }
    @GetMapping("edit/{id}")
    public String update(@PathVariable Long id,Model model)
    {
        Device device=deviceService.findById(id);
        model.addAttribute("device",device);
        model.addAttribute("users",userService.getAll());
        model.addAttribute("statuses", Arrays.stream(Status.values()).collect(Collectors.toList()));

        return "device-edit";
    }
    @PostMapping("/editing/{id}")
    public String editing(Device device,@PathVariable Long id)
    {
        deviceService.update(id,device);
        return "redirect:/devices/" + device.getId();
    }


}
