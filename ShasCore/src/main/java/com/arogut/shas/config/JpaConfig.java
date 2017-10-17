package com.arogut.shas.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = "com.arogut.shas.model")
@EnableJpaRepositories(basePackages = "com.arogut.shas.model")
public class JpaConfig {
}
