package com.ssafy.buttonup.config;

import com.google.common.base.Predicate;
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

/**
 * Swagger UI 환경 설정 파일
 *
 * @author jiun kim
 * created on 2022-02-03
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    private String version = "V1";
    private String title = "Button Up API " + version;

    @Bean
    public Docket postsApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("ButtonUpAPI")
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ssafy.buttonup.controller"))
                .paths(postPaths())
                .build();
    }

    private Predicate<String> postPaths() {
        return PathSelectors.any();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(title)
                .description("Button Up API Reference for Developers")
                .contact(new Contact("ssafy", "https://edu.ssafy.com", "ssafy@ssafy.com"))
                .license("Button Up License")
                .licenseUrl("ssafy@ssafy.com").version("1.0").build();
    }
}
