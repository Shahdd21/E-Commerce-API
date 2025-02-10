package com.project.e_commerce_api.repository;

import com.project.e_commerce_api.entity.Vendor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendorRepository extends JpaRepository<Vendor, Integer> {
}
