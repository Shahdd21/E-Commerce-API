package com.project.e_commerce_api.auth;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register/customer")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody CustomerRegisterRequest request
    ){
        return ResponseEntity.ok(authenticationService.registerCustomer(request));
    }

    @PostMapping("/register/vendor")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody VendorRegisterRequest request) {
        return ResponseEntity.ok(authenticationService.registerVendor(request));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}
