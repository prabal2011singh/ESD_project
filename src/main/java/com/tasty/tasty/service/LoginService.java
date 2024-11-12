// src/main/java/com/tasty/tasty/service/LoginService.java
package com.tasty.tasty.service;

import com.tasty.tasty.dto.LoginRequest;
import com.tasty.tasty.entity.Customer;
import com.tasty.tasty.repo.CustomerRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final CustomerRepo customerRepo;

    public String login(LoginRequest request) {
        Customer customer = customerRepo.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));


        if (request.getPassword().equals(customer.getPassword())) {
            return "User logged in successfully.";
        } else {
            throw new IllegalArgumentException("Invalid email or password.");
        }
    }
}