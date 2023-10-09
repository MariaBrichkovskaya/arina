package com.course.project.arina.services;

import com.course.project.arina.models.Device;
import com.course.project.arina.repositories.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeviceService {
    private final DeviceRepository deviceRepository;
    public List<Device> getAll(){
        return deviceRepository.findAll();
    }
    public Device findById(Long id){
        return deviceRepository.findById(id).orElse(null);
    }
    @Transactional
    public void add(Device device){
        deviceRepository.save(device);
    }
    @Transactional
    public void delete(Long id){
        deviceRepository.deleteById(id);
    }
    @Transactional
    public void update(Device device){
        deviceRepository.save(device);
    }

}
