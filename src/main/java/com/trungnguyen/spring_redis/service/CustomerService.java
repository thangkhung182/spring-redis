package com.trungnguyen.spring_redis.service;

import com.trungnguyen.spring_redis.jpa.entity.Customer;
import com.trungnguyen.spring_redis.jpa.repository.JpaCustomerRepository;
import com.trungnguyen.spring_redis.redis.repository.RedisCustomerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final RedisCustomerDao customerDao;

    private final JpaCustomerRepository customerRepository;

    public final Customer findById(UUID id) {
        var customer = customerDao.findById(id);

        if (customer == null) {
            customer = customerRepository.findById(id).orElse(null);

            if (customer != null) {
                customerDao.save(customer);
            }
        }

        return customer;
    }
}
