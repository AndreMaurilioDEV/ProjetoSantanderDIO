package com.example.dio.projeto.doc;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springdoc.core.models.GroupedOpenApi;
import io.swagger.v3.oas.annotations.info.Contact;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "API Santander Dev Week",
                version = "1.0.0",
                description = "RESTful API da Santander Dev Week 2023 construída em Java 17 com Spring Boot 3.",
                contact = @Contact(
                        name = "André Maurilio",
                        email = "andremaurilio@example.com",
                        url = "https://github.com/AndreMaurilioDEV"
                ),
                license = @License(
                        name = "MIT",
                        url = "https://opensource.org/licenses/MIT"
                )
        )
)

public class OpenApiConfig {
    @Bean
    public GroupedOpenApi api() {
        return GroupedOpenApi.builder()
                .group("v1")
                .packagesToScan("com.example.dio.projeto")
                .build();
    }
}