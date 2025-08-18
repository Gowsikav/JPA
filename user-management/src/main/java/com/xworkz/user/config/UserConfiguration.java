package com.xworkz.user.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@ComponentScan("com.xworkz.user")
@EnableWebMvc
public class UserConfiguration implements WebMvcConfigurer {

    public UserConfiguration()
    {
        System.out.println("UserConfiguration constructor");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/images/**").addResourceLocations("/images/");
        registry.addResourceHandler("/js/**").addResourceLocations("/js/");
    }

    @Bean
    public InternalResourceViewResolver internalResourceViewResolver()
    {
        System.out.println("InternalResourceViewResolver method");
        return new InternalResourceViewResolver("/",".jsp");
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        System.out.println("BCryptPasswordEncoder method");
        return new BCryptPasswordEncoder();
    }
}
