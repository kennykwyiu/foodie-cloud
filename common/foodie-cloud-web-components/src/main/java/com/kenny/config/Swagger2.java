package com.kenny.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2 {

//    http://localhost:8088/swagger-ui.html     Original path
//    http://localhost:8088/doc.html     Original path

    // Configure swagger2 core configuration docket
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)  // Specify api type as swagger2
                    .apiInfo(apiInfo())                 // Used to define api document summary information
                    .select()
                    .apis(RequestHandlerSelectors
                            .basePackage("com.kenny.controller"))   // Specify controller package
                    .paths(PathSelectors.any())         // All controllers
                    .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("KENNY E-commerce Platform API")        // Document page title
                .contact(new Contact("kenny",
                        "https://www.kenny.com",
                        "abc@kenny.com"))        // Contact information
                .description("API documentation for KENNY")  // Detailed information
                .version("1.0.1")   // Document version number
                .termsOfServiceUrl("https://www.kenny.com") // Website address
                .build();
    }

}
