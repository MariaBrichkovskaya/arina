package com.course.project.arina.controllers;

import com.course.project.arina.enums.Status;
import com.course.project.arina.models.Device;
import com.course.project.arina.services.DeviceService;
import com.course.project.arina.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService deviceService;
    private final UserService userService;
    @GetMapping
    public String getAll(@RequestParam(name = "status",required = false)Status status, Model model){
            model.addAttribute("devices",deviceService.getAll(status));
            model.addAttribute("users",userService.getAll());
            model.addAttribute("statuses", Arrays.stream(Status.values()).collect(Collectors.toList()));
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
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(Device device,@PathVariable Long id)
    {
        deviceService.update(id,device);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
