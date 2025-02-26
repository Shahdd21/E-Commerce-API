package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.CustomerDTO;
import com.project.e_commerce_api.entity.Customer;
import com.project.e_commerce_api.entity.User;


public interface CustomerService {

    CustomerDTO getCustomerDetails(User user);
}
