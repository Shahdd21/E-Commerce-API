package com.project.e_commerce_api.service;

import com.project.e_commerce_api.dto.VendorDTO;
import com.project.e_commerce_api.entity.Vendor;

import java.util.List;

public interface VendorService {

    Vendor findById(Integer id);

    Vendor save(Vendor vendor);

    List<VendorDTO> findAll();
}
