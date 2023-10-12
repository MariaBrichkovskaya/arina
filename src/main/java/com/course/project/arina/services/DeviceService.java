package com.course.project.arina.services;

import com.course.project.arina.enums.Status;
import com.course.project.arina.models.Device;

import java.util.List;

public interface DeviceService {
    List<Device> getAll(Status status);

    Device findById(Long id);

    void add(Device device);

    void delete(Long id);

    void update(Long id,Device device);

    List<Device> findByStatus(Status status);
}
