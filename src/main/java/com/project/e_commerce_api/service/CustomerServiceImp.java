package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.CustomerDTO;
import com.project.e_commerce_api.entity.Customer;
import com.project.e_commerce_api.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImp implements CustomerService{

    private final CustomerRepository customerRepository;

    public CustomerServiceImp(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> findAll() {
        List<Customer> customers = customerRepository.findAll();

        List<CustomerDTO> dtos = customers.stream().map(CustomerDTO::new).toList();

        return dtos;
    }
}
