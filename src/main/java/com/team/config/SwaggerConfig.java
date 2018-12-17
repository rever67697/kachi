package com.team.config;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author : wuzhiheng
 * @Description :
 * @Date Created in 11:59 AM 2018/12/17
 */
//@Configuration
//@ConditionalOnProperty(prefix = "swagger",name = "enable",havingValue = "true")
//@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.team.controller"))
                .paths(PathSelectors.any())
//                .paths(PathSelectors.regex("/rest/.*"))
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Kachi系统API")
                .description("Kachi系统API")
                .termsOfServiceUrl("http://127.0.0.1:8080/")
                .contact("xxx")
                .version("1.0")
                .build();
    }

}
