package com.course.project.arina.models;

import com.course.project.arina.enums.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    Role role;
    @Column(name = "name")
    String name;
    @Column(name = "surname")
    String surname;
    @Column(name = "email")
    String email;
    @Column(name = "password")
    String password;
    @OneToMany(mappedBy = "user",fetch = FetchType.LAZY,cascade = CascadeType.ALL, orphanRemoval = true)
    List<Device> devices;

}
