package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.VendorDTO;
import com.project.e_commerce_api.entity.Vendor;
import com.project.e_commerce_api.repository.VendorRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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

    @Override
    public List<VendorDTO> findAll() {
        List<Vendor> vendors = vendorRepository.findAll();

        return vendors.stream().map(VendorDTO::new).toList();
    }
}
