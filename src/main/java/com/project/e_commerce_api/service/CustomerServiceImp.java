package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.CustomerDTO;
import com.project.e_commerce_api.entity.Customer;
import com.project.e_commerce_api.entity.User;
import com.project.e_commerce_api.exception.UserNotFoundException;
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
    public CustomerDTO getCustomerDetails(User user) {

        if(user.getCustomer() == null)
            throw new UserNotFoundException("This endpoint for customers only.");

        return new CustomerDTO(user.getCustomer());
    }
}
