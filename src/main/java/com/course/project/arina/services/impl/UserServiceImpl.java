package com.course.project.arina.services.impl;

import com.course.project.arina.enums.Role;
import com.course.project.arina.models.Device;
import com.course.project.arina.models.User;
import com.course.project.arina.repositories.UserRepository;
import com.course.project.arina.services.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    //@Cacheable(value = "users")
    public List<User> getAll(){
        return userRepository.findAll();
    }
    //@Cacheable(value = "users",key = "#id")
    public User findById(Long id){

        return userRepository.findById(id).orElse(null);
    }
    //@CachePut(value = "users")
    @Transactional
    public boolean add(User user){
        if(userRepository.findByEmail(user.getEmail())!=null){
            return false;
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        log.info("Saving new user with email {}",user.getEmail());
        user.setRole(Role.USER);
        userRepository.save(user);
        return true;
    }

    //@CacheEvict("user")
    @Transactional
    public void delete(Long id){
        userRepository.deleteById(id);
    }
    //@CachePut(value = "users")
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
    public User getUserByPrincipal(Principal principal) {
        if(principal==null)return new User();
        return userRepository.findByEmail(principal.getName());
    }
    public void updatePrincipal(User user) {
        Authentication authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }

}
