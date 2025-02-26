package com.project.e_commerce_api.controller;

import com.project.e_commerce_api.dto.VendorDTO;
import com.project.e_commerce_api.entity.User;
import com.project.e_commerce_api.service.VendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class VendorController {

    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    @GetMapping("/vendors")
    public List<VendorDTO> findAll(){
        return vendorService.findAll();
    }

    @GetMapping("/vendor")
    public VendorDTO getVendorDetails(@AuthenticationPrincipal User user){

        return vendorService.getVendorDetails(user);
    }
}
