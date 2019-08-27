package com.loginportal.deregister.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.loginportal.deregister.service.DeRegisterService;
import com.loginportal.deregister.service.DeRegisterServiceImplementation;

@Configuration
public class DeRegisterConfig {

	@Bean
	public DeRegisterService deRegisterService() {
		return new DeRegisterServiceImplementation();
	}
}
