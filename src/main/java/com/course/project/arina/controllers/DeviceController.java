package com.course.project.arina.controllers;

import com.course.project.arina.dto.DeviceDTO;
import com.course.project.arina.models.Device;
import com.course.project.arina.services.DeviceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/devices")
public class DeviceController {
    private final DeviceService deviceService;
    private final ModelMapper modelMapper;
    @GetMapping
    public List<DeviceDTO> getAll(){
        List<Device> devices = deviceService.getAll();
        return devices.stream()
                .map(device -> new ModelMapper().map(device, DeviceDTO.class))
                .collect(Collectors.toList());

    }
    @GetMapping("/{id}")
    public DeviceDTO getById(@PathVariable Long id){
        return new  ModelMapper().map(deviceService.findById(id),DeviceDTO.class);
    }
    @PostMapping
    public void add(@RequestBody DeviceDTO deviceDTO){
        Device device =modelMapper.map(deviceDTO, Device.class);
        deviceService.add(device);

    }

}
