package com.project.e_commerce_api.dto;

import com.project.e_commerce_api.entity.Vendor;
import jakarta.persistence.Column;

public class VendorDTO {

    private int vendor_id;

    private String name;

    private String email;

    private String address;

    private String phone_number;

    public VendorDTO(Vendor vendor){
        this.vendor_id = vendor.getVendor_id();
        this.name = vendor.getName();
        this.email = vendor.getEmail();
        this.address = vendor.getAddress();
        this.phone_number = vendor.getPhone_number();
    }

    public int getVendor_id() {
        return vendor_id;
    }

    public void setVendor_id(int vendor_id) {
        this.vendor_id = vendor_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    @Override
    public String toString() {
        return "{" +
                "vendor_id=" + vendor_id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", phone_number='" + phone_number + '\'' +
                '}';
    }
}
