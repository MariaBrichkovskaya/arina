package com.course.project.arina.services;

import com.course.project.arina.models.Device;
import com.course.project.arina.models.User;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.List;

public interface UserService {
    List<User> getAll();

    boolean add(User user);

    User findById(Long id);

    void delete(Long id);

    Double getSum(Long id);

    void update(Long id, User user);

    User getUserByPrincipal(Principal principal);

    boolean doPasswordsMatch(String oldPassword, String password);

    String doPasswordEncode(String password);
    void updatePrincipal(User user);
    Double getSum(User user);
}
