package com.test.simplerest;

// import org.apache.catalina.filters.CorsFilter;
import org.springframework.web.filter.CorsFilter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Collections;

@SpringBootApplication
public class SimpleRestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleRestApplication.class, args);
	}

	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowCredentials(true);
		config.setAllowedOrigins(Collections.singletonList("*"));
		config.addAllowedHeader("*");
		config.addAllowedMethod("*");
		source.registerCorsConfiguration("/**", config);
		FilterRegistrationBean<CorsFilter> filterRegistration = new FilterRegistrationBean<>(new CorsFilter(source));
		filterRegistration.setOrder(Ordered.HIGHEST_PRECEDENCE);
		return filterRegistration;
	}
}
