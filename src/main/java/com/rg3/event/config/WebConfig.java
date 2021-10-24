package com.rg3.event.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public WebMvcConfigurer webMvcConfigurer(){
        WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer(){

            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addViewController("/").setViewName("hello");
                registry.addViewController("/encrypt.html").setViewName("/encrypt");
//                registry.addViewController("/decrypt").setViewName("");
                registry.addViewController("/hash.html").setViewName("/hash");
                registry.addViewController("/caculator.html").setViewName("/caculator");
            }
        };
        return  webMvcConfigurer;
    }
}
