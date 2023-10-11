package com.course.project.arina.services;

import com.course.project.arina.models.User;

import java.util.List;

public interface UserService {
    List<User> getAll();

    void add(User user);

    User findById(Long id);

    void delete(Long id);

    Double getSum(Long id);

    void update(Long id, User user);
}
