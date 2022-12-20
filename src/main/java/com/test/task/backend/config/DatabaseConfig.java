package com.test.task.backend.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories("com.test.task.backend.repository")
@EntityScan("com.test.task.backend.domain.model")
@EnableJpaAuditing
public class DatabaseConfig {
}
