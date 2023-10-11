package com.course.project.arina.controllers;

import com.course.project.arina.dto.UserDTO;
import com.course.project.arina.models.Device;
import com.course.project.arina.models.User;
import com.course.project.arina.services.UserService;
import com.course.project.arina.services.impl.UserServiceImpl;
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
    private final ModelMapper modelMapper;
    @PostMapping
    public void add(@RequestBody UserDTO userDTO){
        User user =modelMapper.map(userDTO, User.class);
        userService.add(user);

    }
    @GetMapping
    public List<UserDTO> getAll(){
        List<User> users = userService.getAll();
        return users.stream()
                .map(user -> new ModelMapper().map(user, UserDTO.class))
                .collect(Collectors.toList());

    }
    @GetMapping("/{id}")
    public UserDTO getById(@PathVariable Long id){
        System.out.println(userService.getSum(id));
        return new  ModelMapper().map(userService.findById(id),UserDTO.class);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteById(@PathVariable Long id){
        userService.delete(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<HttpStatus> update(@RequestBody UserDTO userDTO, @PathVariable Long id)
    {
        User user =modelMapper.map(userDTO, User.class);
        userService.update(id,user);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
