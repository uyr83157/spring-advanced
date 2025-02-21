package com.example.springadvanced.config;

import com.example.springadvanced.converter.PersonToStringConverter;
import com.example.springadvanced.converter.StringToPersonConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new StringToPersonConverter());
        registry.addConverter(new PersonToStringConverter());
    }



}
