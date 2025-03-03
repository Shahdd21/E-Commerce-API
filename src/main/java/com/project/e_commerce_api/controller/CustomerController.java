package com.project.e_commerce_api.controller;

import com.project.e_commerce_api.dto.CustomerDTO;
import com.project.e_commerce_api.entity.Customer;
import com.project.e_commerce_api.entity.User;
import com.project.e_commerce_api.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customer")
    public CustomerDTO getCustomerDetails(@AuthenticationPrincipal User user){

        return customerService.getCustomerDetails(user);
    }
}
