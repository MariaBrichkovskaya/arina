package com.course.project.arina.dto;

import com.course.project.arina.enums.Status;
import com.course.project.arina.models.User;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class DeviceDTO {
    LocalDate issueDate;
    Status status;
    String name;
    String brand;
    BigDecimal cost;
    LocalDate acceptanceDate;
    User user;

}
