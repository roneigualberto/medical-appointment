package com.example.app.config;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

	private static final Set<String> PRODUCES_AND_CONSUMES = new HashSet<>(Arrays.asList("application/json"));

	private static final String PKG_CONTROLLER = "com.example.app.controller";

	public ApiInfo apiInfo() {

		Contact contato = new Contact("", "", "");
		return new ApiInfoBuilder().title("API Title").description("API Documentation").version("1.0").contact(contato)
				.license("Apache 2.0").licenseUrl("http://www.apache.org/licences/LICENSE-2.0").build();
	}

	@Bean
	public Docket api() {

		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).produces(PRODUCES_AND_CONSUMES)
				.consumes(PRODUCES_AND_CONSUMES).select().apis(RequestHandlerSelectors.basePackage(PKG_CONTROLLER))
				.build();

	}

}