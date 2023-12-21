package com.jrinehuls.employeemgmt.configuration;

import com.jrinehuls.employeemgmt.mapper.EmployeeMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public EmployeeMapper getEmployeeMapper() {
        return new EmployeeMapper();
    }
}
