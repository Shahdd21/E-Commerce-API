package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.AdminRequest;
import com.project.e_commerce_api.dto.UserDTO;
import com.project.e_commerce_api.entity.User;
import com.project.e_commerce_api.enums.UserRole;
import com.project.e_commerce_api.exception.UsernameAlreadyExistsException;
import com.project.e_commerce_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImp(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();

        List<UserDTO> dtos = users.stream().map(UserDTO::new).toList();

        return dtos;
    }

    @Override
    public User findById(Integer userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public void deleteById(Integer userId) {
       userRepository.deleteById(userId);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public String addAdmin(AdminRequest adminRequest) {

        if(userRepository.findByUsername(adminRequest.getUsername()).isPresent()){
            throw new UsernameAlreadyExistsException("Username already exists. try new one.");
        }

        User user = new User();
        user.setUsername(adminRequest.getUsername());
        user.setPassword(passwordEncoder.encode(adminRequest.getPassword()));
        user.setRole(UserRole.ROLE_ADMIN);
        user.setApproved(true);

        User savedUser = userRepository.save(user);

        return "User with id: "+savedUser.getUser_id()+" is now an admin.";
    }
}
