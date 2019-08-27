package com.loginportal.deregister.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.loginportal.deregister.model.CustomPasswordEncoder;

@Configuration
public class Authenticateconfig {
	@Bean
    public CustomPasswordEncoder customPasswordEncoder() {
        return new CustomPasswordEncoder();
    }
}


