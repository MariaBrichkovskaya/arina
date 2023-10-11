package com.course.project.arina.services.impl;

import com.course.project.arina.models.Device;
import com.course.project.arina.models.User;
import com.course.project.arina.repositories.UserRepository;
import com.course.project.arina.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    @Cacheable(value = "users")
    public List<User> getAll(){
        return userRepository.findAll();
    }
    @Cacheable(value = "users",key = "#id")
    public User findById(Long id){

        return userRepository.findById(id).orElse(null);
    }
    @CachePut(value = "users")
    @Transactional
    public void add(User user){
        userRepository.save(user);
    }
    @CacheEvict("user")
    @Transactional
    public void delete(Long id){
        userRepository.deleteById(id);
    }
    @CachePut(value = "users")
    @Transactional
    public void update(Long id ,User user){
        User userToEdit=userRepository.findById(id).orElse(null);
        assert userToEdit != null;
        editing(user,userToEdit);
        userRepository.save(user);
    }
    private void editing(User user,User userToEdit){
        userToEdit.setName(user.getName());
        userToEdit.setRole(user.getRole());
        userToEdit.setEmail(user.getEmail());
        userToEdit.setPassword(user.getPassword());
        userToEdit.setSurname(user.getSurname());
    }

    public Double getSum(Long id){
        User sumUser =userRepository.findById(id).orElse(null);
        assert sumUser != null;
        if(sumUser.getDevices().isEmpty()) return 0.0;
        return sumUser.getDevices().stream().mapToDouble(Device::getCost).sum();
    }


}
