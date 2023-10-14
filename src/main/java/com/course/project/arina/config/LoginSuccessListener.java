package com.course.project.arina.config;

import com.course.project.arina.controllers.AppController;
import com.course.project.arina.models.User;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class LoginSuccessListener implements ApplicationListener<AuthenticationSuccessEvent> {
    private final AppController appController;
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent evt) {
        appController.user = (User) evt.getAuthentication().getPrincipal();
        System.out.println(appController.user.getEmail() + " has just logged in");

    }
}
