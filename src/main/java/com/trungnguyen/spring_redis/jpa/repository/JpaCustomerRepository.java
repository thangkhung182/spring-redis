package com.trungnguyen.spring_redis.jpa.repository;

import com.trungnguyen.spring_redis.jpa.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCustomerRepository extends JpaRepository<Customer, UUID> {
}
