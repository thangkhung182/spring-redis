package com.trungnguyen.spring_redis;

import com.trungnguyen.spring_redis.jpa.entity.Customer;
import com.trungnguyen.spring_redis.jpa.repository.JpaCustomerRepository;
import com.trungnguyen.spring_redis.redis.repository.RedisCustomerDao;
import com.trungnguyen.spring_redis.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
