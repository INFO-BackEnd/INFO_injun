package org.example.info_injun.global.config;

import org.example.info_injun.global.error.GlobalExceptionFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

    @Bean
    public FilterRegistrationBean<GlobalExceptionFilter> registerGlobalExceptionFilter() {
        FilterRegistrationBean<GlobalExceptionFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new GlobalExceptionFilter());
        registrationBean.addUrlPatterns("/*");
        return registrationBean;
    }
}
