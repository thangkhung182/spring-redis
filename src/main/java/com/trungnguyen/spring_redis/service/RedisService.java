package com.trungnguyen.spring_redis.service;

import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedissonClient redissonClient;

    public RLock getLock(String lockName) {
        return redissonClient.getLock(lockName);
    }
}
