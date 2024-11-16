package com.trungnguyen.spring_redis;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringRedisApplicationTests {
	@Autowired
	private RedissonClient redissonClient;

	@Test
	void contextLoads() {
		Assertions.assertNotNull(redissonClient);
	}

}
