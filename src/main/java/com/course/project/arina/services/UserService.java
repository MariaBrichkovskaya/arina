package com.course.project.arina.services;

import com.course.project.arina.models.Device;
import com.course.project.arina.models.User;
import com.course.project.arina.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    public List<User> getAll(){
        return userRepository.findAll();
    }
    public User findById(Long id){
        return userRepository.findById(id).orElse(null);
    }
    @Transactional
    public void add(User user){
        userRepository.save(user);
    }
    @Transactional
    public void delete(Long id){
        userRepository.deleteById(id);
    }
    @Transactional
    public void update(User user){
        userRepository.save(user);
    }
    public Double getSum(User user){
        User sumUser =userRepository.findById(user.getId()).orElse(null);
        assert sumUser != null;
        if(sumUser.getDevices().isEmpty()) return 0.0;
        return sumUser.getDevices().stream().mapToDouble(Device::getCost).sum();
    }


}
