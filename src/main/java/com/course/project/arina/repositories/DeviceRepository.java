package com.course.project.arina.repositories;

import com.course.project.arina.enums.Status;
import com.course.project.arina.models.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Long> {
    List<Device> findDeviceByStatus(Status status);
    List<Device> findAllByOrderByIdDesc();
}
