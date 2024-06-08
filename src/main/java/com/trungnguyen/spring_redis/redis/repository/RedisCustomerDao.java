package com.trungnguyen.spring_redis.redis.repository;

import com.trungnguyen.spring_redis.jpa.entity.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public class RedisCustomerDao {
    private static final String HASH_KEY = "customer";

    @Autowired
    @Qualifier("customerRedisTemplate")
    private RedisTemplate redisTemplate;

    public void save(Customer customer) {
        redisTemplate.opsForHash().put(HASH_KEY, customer.getId().toString(), customer);
    }

    public Customer findById(UUID id) {
        return (Customer) redisTemplate.opsForHash().get(HASH_KEY, id.toString());
    }
}
