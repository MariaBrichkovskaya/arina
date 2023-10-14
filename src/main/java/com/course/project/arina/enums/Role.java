package com.course.project.arina.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    USER("Пользователь"),
    ADMIN("Администратор");
    public final String name;
    Role (String name) {
        this.name = name;
    }
    @Override
    public String getAuthority() {
        return name();
    }
}
