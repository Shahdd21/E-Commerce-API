package com.project.e_commerce_api.controller;

import com.project.e_commerce_api.dto.UserDTO;
import com.project.e_commerce_api.entity.User;
import com.project.e_commerce_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<UserDTO> findAll(){
        return userService.findAll();
    }

    @GetMapping("/users/{userId}")
    public UserDTO findById(@PathVariable Integer userId){

        UserDTO user = userService.findById(userId);

        if(user == null) throw new RuntimeException("No user with user id - " + userId);

        return user;
    }

    @DeleteMapping("/users/{userId}")
    public String deleteById(@PathVariable Integer userId){

        UserDTO user = userService.findById(userId);

        if(user == null) throw new RuntimeException("No user with user id - " + userId);

        userService.deleteById(userId);

        return "Deleted user - "+ user;
    }
}
