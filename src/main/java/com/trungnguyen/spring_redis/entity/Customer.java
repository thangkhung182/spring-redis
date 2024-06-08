package com.trungnguyen.spring_redis.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;


import java.util.UUID;

@Entity
public class Customer {
    @Id
    private UUID id;

    @Column(nullable = false)
    private String name;

    private String phone;
}
