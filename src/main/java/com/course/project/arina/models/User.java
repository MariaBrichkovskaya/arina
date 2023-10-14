package com.course.project.arina.models;

import com.course.project.arina.enums.Role;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="users")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Enumerated(EnumType.STRING)
    @Column(name = "role",nullable = false)
    Role role;
    @Column(name = "name",nullable = false)
    String name;
    @Column(name = "surname",nullable = false)
    String surname;
    @Column(name = "email",nullable = false)
    String email;
    @Column(name = "password",nullable = false)
    String password;
    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER,cascade = CascadeType.ALL, orphanRemoval = true)
    List<Device> devices;

    public boolean isUser(){
        return role.equals(Role.USER);
    }
    public boolean isAdmin(){
        return role.equals(Role.ADMIN);
    }
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(role);
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
