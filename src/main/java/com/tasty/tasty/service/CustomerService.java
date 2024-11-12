package com.tasty.tasty.service;

import com.tasty.tasty.dto.CustomerRequest;
import com.tasty.tasty.dto.CustomerResponse;
import com.tasty.tasty.entity.Customer;
import com.tasty.tasty.mapper.CustomerMapper;
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
    public String createCustomer(CustomerRequest request) {
        Customer customer = mapper.toEntity(request);
        repo.save(customer);
        return "Created";
    }
}