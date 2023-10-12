package com.course.project.arina.controllers;

import com.course.project.arina.models.User;
import com.course.project.arina.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {
    private final UserService userService;
    @PostMapping
    public void add(@RequestBody User user){
        userService.add(user);

    }
    @GetMapping
    public List<User> getAll(){
        return userService.getAll();

    }
    @GetMapping("/{id}")
    public User getById(@PathVariable Long id){
        System.out.println(userService.getSum(id));
        return userService.findById(id);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(User user, @PathVariable Long id)
    {
        userService.update(id,user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
