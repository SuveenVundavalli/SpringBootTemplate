package me.suveen.portfolio.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableJpaRepositories(basePackages = "me.suveen.portfolio.backend.persistence.repositories")
@EntityScan(basePackages =  "me.suveen.portfolio.backend.persistence.domain.backend")
@EnableTransactionManagement
public class ApplicationConfig {

}
