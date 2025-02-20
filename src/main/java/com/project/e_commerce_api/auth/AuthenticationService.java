package com.project.e_commerce_api.auth;

import com.project.e_commerce_api.config.JwtService;
import com.project.e_commerce_api.entity.Customer;
import com.project.e_commerce_api.entity.User;
import com.project.e_commerce_api.entity.Vendor;
import com.project.e_commerce_api.entity.Wishlist;
import com.project.e_commerce_api.enums.UserRole;
import com.project.e_commerce_api.repository.CustomerRepository;
import com.project.e_commerce_api.repository.UserRepository;
import com.project.e_commerce_api.repository.VendorRepository;
import com.project.e_commerce_api.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AuthenticationService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final VendorRepository vendorRepository;
    private final WishlistRepository wishlistRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    @Autowired
    public AuthenticationService(UserRepository userRepository, CustomerRepository customerRepository, VendorRepository vendorRepository, WishlistRepository wishlistRepository, PasswordEncoder passwordEncoder, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.vendorRepository = vendorRepository;
        this.wishlistRepository = wishlistRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }

    public AuthenticationResponse registerCustomer(CustomerRegisterRequest request) {

        Customer customer = new Customer(request.getFirstName(), request.getLastName(), request.getEmail(),
                request.getAddress(), request.getPhoneNumber(), request.getGender());

        User user = new User(request.getFirstName()+"user", passwordEncoder.encode(request.getPassword()),
                UserRole.ROLE_CUSTOMER, true);

        userRepository.save(user);
        customer.setUser(user);
        Customer savedCustomer = customerRepository.save(customer);

        Wishlist wishlist = new Wishlist();
        wishlist.setCustomer(savedCustomer);
        wishlistRepository.save(wishlist);

        var jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse registerVendor(VendorRegisterRequest request) {

        Vendor vendor = new Vendor(request.getName(), request.getEmail(), request.getAddress(),
                request.getPhoneNumber(), new ArrayList<>());

        User user = new User(request.getEmail(), passwordEncoder.encode(request.getPassword()),
                UserRole.ROLE_VENDOR, true);

        userRepository.save(user);

        vendor.setUserObject(user);
        vendorRepository.save(vendor);

        var jwtToken = jwtService.generateToken(user);

        return new AuthenticationResponse(jwtToken);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(), request.getPassword()
                )
        );

        //if i reach this point, the user is authenticated
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow();

        var jwtToken = jwtService.generateToken(user);
        System.out.println("\n GENERATED TOKEN: "+jwtToken+"\n");

        return new AuthenticationResponse(jwtToken);
    }
}
