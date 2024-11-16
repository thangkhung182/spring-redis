package com.trungnguyen.spring_redis.service;

import com.trungnguyen.spring_redis.jpa.entity.Customer;
import com.trungnguyen.spring_redis.jpa.repository.JpaCustomerRepository;
import com.trungnguyen.spring_redis.redis.repository.RedisCustomerDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

@Service
@RequiredArgsConstructor
public class CustomerService {
    private final RedisCustomerDao redisCustomerDao;

    private final JpaCustomerRepository customerRepository;

    private final RedisService redisService;

    public final Customer findById(UUID id) {
        var customer = redisCustomerDao.findById(id);

        if (customer == null) {
            var redisLock = redisService.getLock("customer-lock-" + id);

            try {
                boolean locked = !redisLock.tryLock(1, 5, TimeUnit.SECONDS);

                if (!locked) {
                    // Re check redis cache !!!
                    customer = redisCustomerDao.findById(id);

                    if (customer == null) {
                        customer = customerRepository.findById(id).orElse(null);
                        redisCustomerDao.save(customer);
                    }
                }

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        return customer;
    }
}
