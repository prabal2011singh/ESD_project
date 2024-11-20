package com.tasty.tasty.service;

import com.tasty.tasty.dto.CustomerRequest;
import com.tasty.tasty.dto.CustomerResponse;
import com.tasty.tasty.entity.Customer;
import com.tasty.tasty.mapper.CustomerMapper;
import com.tasty.tasty.helper.EncryptionService;
import com.tasty.tasty.repo.CustomerRepo;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepo repo;
    private final CustomerMapper mapper;
    private final EncryptionService encryptionService;

    public String createCustomer(CustomerRequest request) {
        if (repo.findByEmail(request.email()).isPresent()) {
            return "Email already exists";
        }

        Customer customer = mapper.toEntity(request);
        // Hash the password before saving
        customer.setPassword(encryptionService.encode(request.password()));
        repo.save(customer);
        return "Created";
    }
}