package com.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

import javax.activation.DataSource;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Arrays;

@SpringBootApplication
@EnableJpaRepositories("com.example.repository")
public class Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(Application.class);

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(ApplicationContext context) {
        return a -> {
            LOGGER.info("Inspecting beans provided by Spring Boot:");

            String[] beanNames = context.getBeanDefinitionNames();
            Arrays.sort(beanNames);
            for (String beanName: beanNames) {
                LOGGER.info("BeanName: " + beanName);
            }
        };
    }

//    @Bean
//    @ConfigurationProperties("spring.datasource")
//    public DataSource getDataSource() {
//        return
//    }

//    @Bean
//    public EntityManagerFactory entityManagerFactory() {
//        return Persistence.createEntityManagerFactory("UserRepository");
//    }
}