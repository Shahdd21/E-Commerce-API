package com.project.e_commerce_api.controller;

import com.project.e_commerce_api.dto.AdminRequest;
import com.project.e_commerce_api.dto.UserDTO;
import com.project.e_commerce_api.entity.User;
import com.project.e_commerce_api.enums.UserRole;
import com.project.e_commerce_api.exception.UnauthorizedAccessException;
import com.project.e_commerce_api.exception.UserNotFoundException;
import com.project.e_commerce_api.exception.VendorNotFoundException;
import com.project.e_commerce_api.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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

        User user = userService.findById(userId);

        if(user == null) throw new UserNotFoundException("No user with user id - " + userId);

        return new UserDTO(user);
    }

    @DeleteMapping("/users/{userId}")
    public String deleteById(@PathVariable Integer userId){

        User user = userService.findById(userId);

        if(user == null) throw new UserNotFoundException("No user with user id - " + userId);

        if(user.getRole() == UserRole.ROLE_SUPER_ADMIN)
            throw new UnauthorizedAccessException("Can not delete Super Admin");

        userService.deleteById(userId);

        return "Deleted user - "+ user;
    }

    @PutMapping("/vendors/approve/{userId}")
    public String approveVendors(@PathVariable Integer userId){

        User user = userService.findById(userId);
        if(user == null) throw new UserNotFoundException("No user with user id - " + userId);

        if (user.isApproved()) {
            return "User: " + user.getUsername() + " is already approved.";
        }

        if (user.getRole() != UserRole.ROLE_VENDOR) {
            throw new VendorNotFoundException("Only vendors can be approved.");
        }

        user.setApproved(true);
        userService.save(user);

        return "user: "+ user.getUsername()+" is approved successfully";
    }

    @PostMapping
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    public String addAdmin(@RequestBody AdminRequest adminRequest){

        return userService.addAdmin(adminRequest);
    }

}
