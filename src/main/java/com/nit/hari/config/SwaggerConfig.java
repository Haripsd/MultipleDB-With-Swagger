package com.nit.hari.config;

import java.util.ArrayList;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
	@Bean
	public Docket createDocket() {
		//create Empty Docket
		return new Docket(DocumentationType.SWAGGER_2)
				.select()  // find Controller Class
				// provide all controller common Package
				.apis(RequestHandlerSelectors.basePackage("com.nit.hari.controller"))
				// Provide One Common Path For Controller
				.paths(PathSelectors.regex("/rest.*"))
				.build() // Create Docket With Details
				.apiInfo(apiInfo());
	}
	
	
	public ApiInfo apiInfo() {
		return new ApiInfo("WELCOME TO MULTIPLE DB APPLICATIONS", "This is for Multiple DataBases", "1.0","https://nareshit.in/",
				new Contact("HARIPRASAD", "http://nareshit.in", "hariprasad@gmail.com"),
				"MY LICENCE", "https://nareshit.in",new ArrayList<VendorExtension>());
	}
	

}
