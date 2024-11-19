package com.tasty.tasty.service;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

import com.tasty.tasty.dto.CustomerRequest;
import com.tasty.tasty.dto.LoginRequest;
import com.tasty.tasty.entity.Customer;
import com.tasty.tasty.mapper.LoginMapper;
import com.tasty.tasty.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.tasty.tasty.helper.EncryptionService;
import com.tasty.tasty.helper.JWTHelper;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final CustomerRepo repo;
    private final LoginMapper mapper;
    private final EncryptionService encryptionService;
    private final JWTHelper jwtHelper;

    public String loginUser(LoginRequest request) {
        Customer customer = mapper.toEntity(request);
        // Attempt to find the customer in the database
        Optional<Customer> existingCustomer = repo.findByEmail(customer.getEmail());

        if (existingCustomer.isPresent()) {
            // Check if the password matches
            if(!encryptionService.validates(request.password(), existingCustomer.get().getPassword())) {
                return jwtHelper.generateToken(request.email());
                //return "Wrong Password or Email";
            }
            else {
                return jwtHelper.generateToken(request.email());
            }
        } else {
            return "User not found";
        }
    }
}