package com.trungnguyen.spring_redis.controller;

import com.trungnguyen.spring_redis.jpa.entity.Customer;
import com.trungnguyen.spring_redis.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

   @GetMapping("/{id}")
    public Customer findById(@PathVariable UUID id) {
        return customerService.findById(id);
    }
}
