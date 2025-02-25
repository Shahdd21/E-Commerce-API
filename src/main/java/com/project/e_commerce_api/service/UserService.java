package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.UserDTO;
import com.project.e_commerce_api.entity.User;

import java.util.List;

public interface UserService {

    List<UserDTO> findAll();

    User findById(Integer userId);

    void deleteById(Integer userId);

    User save(User user);
}
