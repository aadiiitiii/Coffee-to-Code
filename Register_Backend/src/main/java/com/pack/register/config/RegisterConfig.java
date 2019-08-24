package com.pack.register.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pack.register.model.CustomPasswordEncoder;

@Configuration
public class RegisterConfig {

	@Bean
    public CustomPasswordEncoder customPasswordEncoder() {
        return new CustomPasswordEncoder();
    }
}
