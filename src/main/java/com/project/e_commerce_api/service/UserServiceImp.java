package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.UserDTO;
import com.project.e_commerce_api.entity.User;
import com.project.e_commerce_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImp implements UserService{

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImp(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();

        List<UserDTO> dtos = users.stream().map(UserDTO::new).toList();

        return dtos;
    }

    @Override
    public UserDTO findById(Integer userId) {
        return new UserDTO(userRepository.findById(userId).orElse(null));
    }

    @Override
    public void deleteById(Integer userId) {
       userRepository.deleteById(userId);
    }
}
