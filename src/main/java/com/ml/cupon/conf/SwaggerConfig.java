package com.ml.cupon.conf;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RestController;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket api() {
		Docket docket = new Docket(DocumentationType.SWAGGER_2).host("http://localhost:8080")
				.select().apis(RequestHandlerSelectors.withClassAnnotation(RestController.class))
				.paths(PathSelectors.any()).build();

		return docket;
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo("Prueba Coupon", "Order Service API Description", "1.0",
				"https://whispering-forest-20515.herokuapp.com",
				new Contact("Coupon", "https://whispering-forest-20515.herokuapp.com", "mejiam756@gmail.com"),
				"LICENSE", "LICENSE URL", new ArrayList<>());
	}

}
