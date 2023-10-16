package com.course.project.arina.services.impl;

import com.course.project.arina.enums.Status;
import com.course.project.arina.models.Device;
import com.course.project.arina.repositories.DeviceRepository;
import com.course.project.arina.services.DeviceService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DeviceServiceImpl implements DeviceService {
    private final DeviceRepository deviceRepository;
    //@Cacheable(value = "devices")
    //@CachePut(value = "devices")
    public List<Device> getAll(Status status){
        if(status==null) return deviceRepository.findAll();
        else return findByStatus(status);
    }
    //@CachePut(value = "devices",key = "#id")
    public Device findById(Long id){
        return deviceRepository.findById(id).orElse(null);
    }
    @Transactional
    //@CachePut(value = "devices")
    public void add(Device device){
        device.setStatus(Status.PENDING);
        device.setIssueDate(LocalDate.now());
        deviceRepository.save(device);
    }
    @Transactional
    //@CacheEvict("devices")
    public void delete(Long id){
        deviceRepository.deleteById(id);
    }
    //@CachePut(value = "devices",key = "#id")
    @Transactional
    public void update(Long id,Device device){
        Device deviceToEdit=deviceRepository.findById(id).orElse(null);
        assert deviceToEdit != null;
        editing(device,deviceToEdit);
        deviceRepository.save(device);
    }
    private void editing(Device device,Device deviceToEdit){
        LocalDate date=deviceToEdit.getIssueDate();
        deviceToEdit.setId(device.getId());
        deviceToEdit.setIssueDate(date);
        device.setIssueDate(date);
        deviceToEdit.setStatus(device.getStatus());
        deviceToEdit.setUser(device.getUser());
        deviceToEdit.setName(device.getName());
        deviceToEdit.setCost(device.getCost());
        deviceToEdit.setBrand(device.getBrand());
        deviceToEdit.setAcceptanceDate(device.getAcceptanceDate());
        deviceToEdit.setStatus(device.getStatus());
    }
    public List<Device> sortByDate(List<Device> devices) {
        return devices.stream().sorted(Comparator.comparing(Device::getIssueDate)).collect(Collectors.toList());
    }
    public List<Device> sortByStatus(List<Device> devices) {
        return devices.stream().sorted(Comparator.comparing(Device::getStatus)).collect(Collectors.toList());
    }
    public List<Device> findByStatus(Status status){
        return deviceRepository.findDeviceByStatus(status);
    }

}
