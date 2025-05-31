package com.kenny.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

    public CorsConfig() {
    }

    @Bean
    public CorsFilter corsFilter() {
        // 1. Add CORS configuration information
        CorsConfiguration config = new CorsConfiguration();
        config.addAllowedOrigin("http://localhost:8080");
        config.addAllowedOrigin("http://shop.z.mukewang.com:8080");
        config.addAllowedOrigin("http://center.z.mukewang.com:8080");
        config.addAllowedOrigin("http://shop.z.mukewang.com");
        config.addAllowedOrigin("http://center.z.mukewang.com");
        config.addAllowedOrigin("*");

        // Set whether to send cookie information
        config.setAllowCredentials(true);

        // Set allowed request methods
        config.addAllowedMethod("*");

        // Set allowed headers
        config.addAllowedHeader("*");

        // 2. Add mapping path for URL
        UrlBasedCorsConfigurationSource corsSource = new UrlBasedCorsConfigurationSource();
        corsSource.registerCorsConfiguration("/**", config);

        // 3. Return redefined corsSource
        return new CorsFilter(corsSource);
    }

}
