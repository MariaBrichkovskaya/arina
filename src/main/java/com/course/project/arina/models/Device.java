package com.course.project.arina.models;

import com.course.project.arina.enums.Status;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name="devices")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Device {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "issue_date")
    LocalDate issueDate;
    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    Status status;
    @Column(name = "name")
    String name;
    @Column(name = "brand")
    String brand;
    @Column(name = "repair_cost")
    BigDecimal cost;
    @Column(name = "acceptance_date")
    LocalDate acceptanceDate;
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "user_id",referencedColumnName = "id")
    User user;

}
