package com.tasty.tasty.mapper;

import com.tasty.tasty.dto.LoginRequest;
import com.tasty.tasty.entity.Customer;
import org.springframework.stereotype.Service;

@Service
public class LoginMapper {
    public Customer toEntity(LoginRequest request) {
        return Customer.builder()
                .email(request.email())
                .password(request.password())
                .build();
    }
}
