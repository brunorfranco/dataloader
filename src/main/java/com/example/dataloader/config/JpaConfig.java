package com.example.dataloader.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories(basePackages="com.example.dataloader.repository")
@Configuration
public class JpaConfig {

}
