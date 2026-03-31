package com.api.mygym.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

    // A aplicação frontend só poderá consumir minha api vindo dessa url e porta e utilizar esses métodos
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("*//")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD", "TRACE", "CONNECT");
    }

}
