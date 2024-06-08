package com.trungnguyen.spring_redis.config;

import com.trungnguyen.spring_redis.jpa.entity.Customer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class AppConfig {
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean("customerRedisTemplate")
    public RedisTemplate<String, Customer> customerRedisTemplate() {
        var stdSerializer =  new StringRedisSerializer();
        var template = new RedisTemplate<String, Customer>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(stdSerializer);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new Jackson2JsonRedisSerializer<>(Customer.class));
        return template;
    }
}
