package com.project.e_commerce_api.dto;

import com.project.e_commerce_api.entity.Customer;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class CustomerDTO {

    private int customer_id;
    private String first_name;
    private String last_name;
    private String email;
    private String address;
    private String phone_number;
    private char gender;


    public CustomerDTO(Customer customer) {
        this.customer_id = customer.getCustomer_id();
        this.first_name = customer.getFirst_name();
        this.last_name = customer.getLast_name();
        this.email = customer.getEmail();
        this.address = customer.getAddress();
        this.phone_number = customer.getPhone_number();
        this.gender = customer.getGender();
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }
}
