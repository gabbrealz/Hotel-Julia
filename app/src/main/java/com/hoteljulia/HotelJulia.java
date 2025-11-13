package com.hoteljulia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication(scanBasePackages = {"com.hoteljulia.core", "com.hoteljulia.web"})
@EnableJpaRepositories(basePackages = "com.hoteljulia.core.repository")
@EntityScan(basePackages = "com.hoteljulia.core.model")
public class HotelJulia {
    public static void main(String[] args) {
        SpringApplication.run(HotelJulia.class, args);
    }
}