package es.jugmadrid.springboot3.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "es.jugmadrid.springboot3.dao.repository")
@EntityScan(basePackages = "es.jugmadrid.springboot3.dao")
@ComponentScan(basePackages = {"es.jugmadrid.springboot3"})
public class CarApiConfig {
}