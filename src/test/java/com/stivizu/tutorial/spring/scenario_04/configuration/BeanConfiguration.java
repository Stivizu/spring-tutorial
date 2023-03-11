package com.stivizu.tutorial.spring.scenario_04.configuration;

import com.stivizu.tutorial.spring.scenario_04.service.JavaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {

    @Bean
    public JavaService javaService() {
        return new JavaService();
    }

}
