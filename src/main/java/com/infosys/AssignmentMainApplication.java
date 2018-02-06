package com.infosys;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.SecurityAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
@EnableAutoConfiguration
@ComponentScan(basePackages = {"com.infosys"})
@ConfigurationProperties(prefix = "spring")
@EnableSwagger2
public class AssignmentMainApplication extends WebMvcConfigurerAdapter
{
    @Autowired
    private Environment env;

    public static void main(String[] args)
    {
        SpringApplication.run(AssignmentMainApplication.class, args);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry)
    {
        registry.addInterceptor(new LoggingInterceptor()).addPathPatterns("/");
    }


    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build().apiInfo(apiInfo());
    }

    private ApiInfo apiInfo()
    {
        return new ApiInfo(
                "Assigment Services API",
                "This API allows the create test api's",
                "1.0",
                "http://localhost:8888/api",
                new Contact("Madhu Kundala", "", "madhu.kundala@infosys.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0.html");

    }


    @Bean
    public RestTemplate restTemplate()
    {
        return new RestTemplate();
    }
}
