package com.project.e_commerce_api.service;

import com.project.e_commerce_api.entity.Vendor;

public interface VendorService {

    Vendor findById(Integer id);

    Vendor save(Vendor vendor);
}
