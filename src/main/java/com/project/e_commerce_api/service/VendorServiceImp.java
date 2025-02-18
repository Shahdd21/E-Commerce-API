package com.project.e_commerce_api.service;

import com.project.e_commerce_api.entity.Vendor;
import com.project.e_commerce_api.repository.VendorRepository;

public class VendorServiceImp implements VendorService{

    private final VendorRepository vendorRepository;

    public VendorServiceImp(VendorRepository vendorRepository) {
        this.vendorRepository = vendorRepository;
    }

    @Override
    public Vendor findById(Integer id) {
        return vendorRepository.findById(id).orElse(null);
    }

    @Override
    public Vendor save(Vendor vendor) {
        return vendorRepository.save(vendor);
    }
}
