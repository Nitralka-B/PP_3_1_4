package ru.kata.spring.boot_security.demo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/user-page").setViewName("userPage");
        registry.addViewController("/admin").setViewName("adminPage");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/header.html")
                .addResourceLocations("classpath:/templates/header.html");
        registry.addResourceHandler("/sidebar.html")
                .addResourceLocations("classpath:/templates/sidebar.html");
    }
}