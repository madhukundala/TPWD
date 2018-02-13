package com.infosys;

import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

public class SecurityConfig {

	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Assigment Services API", "This API allows the create test api's", "1.0",
				"http://localhost:8888/api", new Contact("Madhu Kundala", "", "madhu.kundala@infosys.com"),
				"Apache 2.0", "http://www.apache.org/licenses/LICENSE-2.0.html");

	}

}
