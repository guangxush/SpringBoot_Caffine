package com.shgx.caffine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CaffineApplication {

    public static void main(String[] args) {
        SpringApplication.run(CaffineApplication.class, args);
    }

}

