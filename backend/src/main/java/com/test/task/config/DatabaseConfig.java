package com.test.task.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.test.task.repository")
@EntityScan("com.test.task.domain.model")
@EnableJpaAuditing
public class DatabaseConfig {
}
