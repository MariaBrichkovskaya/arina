package com.course.project.arina.controllers;

import com.course.project.arina.dto.DeviceDTO;
import com.course.project.arina.enums.Status;
import com.course.project.arina.models.Device;
import com.course.project.arina.services.DeviceService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public List<DeviceDTO> getAll(@RequestParam(name = "status",required = false)Status status){
        if (status==null){
            return deviceService.getAll().stream()
                    .map(device -> new ModelMapper().map(device, DeviceDTO.class))
                    .collect(Collectors.toList());
        } else{
            return deviceService.findByStatus(status).stream()
                    .map(device -> new ModelMapper().map(device, DeviceDTO.class))
                    .collect(Collectors.toList());
        }


    }
    @GetMapping("/{id}")
    public DeviceDTO getById(@PathVariable Long id){
        return new  ModelMapper().map(deviceService.findById(id),DeviceDTO.class);
    }
    @PostMapping
    public ResponseEntity<HttpStatus> add(@RequestBody DeviceDTO deviceDTO){
        Device device =modelMapper.map(deviceDTO, Device.class);
        deviceService.add(device);
        return ResponseEntity.ok(HttpStatus.OK);

    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id){
        deviceService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody DeviceDTO deviceDTO,@PathVariable Long id)
    {
        Device device =modelMapper.map(deviceDTO, Device.class);
        deviceService.update(id,device);
        return ResponseEntity.ok(HttpStatus.OK);
    }

}
