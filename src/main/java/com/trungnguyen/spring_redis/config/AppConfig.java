package com.trungnguyen.spring_redis.config;

import com.trungnguyen.spring_redis.jpa.entity.Customer;
import org.aspectj.weaver.ast.Or;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.redisson.spring.data.connection.RedissonConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.Resource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.IOException;

@Configuration
public class AppConfig {
//    @Bean
//    public RedisConnectionFactory redisConnectionFactory() {
//        return new JedisConnectionFactory();
//    }

    @Bean("customerRedisTemplate")
    public RedisTemplate<String, Customer> customerRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        var stdSerializer =  new StringRedisSerializer();
        var template = new RedisTemplate<String, Customer>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(stdSerializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Customer.class));
        return template;
    }

    @Bean
    public RedissonConnectionFactory redissonConnectionFactory(RedissonClient redisson) {
        return new RedissonConnectionFactory(redisson);
    }

    @Bean(destroyMethod = "shutdown")
    public RedissonClient redisson(@Value("classpath:/redisson.yaml") Resource configFile) throws IOException {
        Config config = Config.fromYAML(configFile.getInputStream());
        return Redisson.create(config);
    }
}
