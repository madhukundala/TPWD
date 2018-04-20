package com.infosys;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.infosys"})
@ConfigurationProperties(prefix = "spring")
@EnableSwagger2
@EnableScheduling
public class AssignmentMainApplication
{

    public static void main(String[] args)
    {
        SpringApplication.run(AssignmentMainApplication.class, args);
    }


}
