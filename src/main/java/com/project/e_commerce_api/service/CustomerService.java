package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.CustomerDTO;
import com.project.e_commerce_api.entity.Customer;

import java.util.List;

public interface CustomerService {
    List<CustomerDTO> findAll();
}
