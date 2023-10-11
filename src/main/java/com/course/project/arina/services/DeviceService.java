package com.course.project.arina.services;

import com.course.project.arina.enums.Status;
import com.course.project.arina.models.Device;
import com.course.project.arina.repositories.DeviceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
        device.setIssueDate(LocalDate.now());
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
    public List<Device> sortByDate() {
        return deviceRepository.findAll().stream().sorted(Comparator.comparing(Device::getIssueDate)).collect(Collectors.toList());
    }
    public List<Device> sortByStatus() {
        return deviceRepository.findAll().stream().sorted(Comparator.comparing(Device::getStatus)).collect(Collectors.toList());
    }
    public List<Device> findByStatus(Status status){
        return deviceRepository.findDeviceByStatus(status);
    }
}
