package com.drogueria.drogueria.Documentacion;

import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API - BeautyApp")
                        .version("1.0.0")
                        .description("Documentación del backend con Swagger UI")
                        .contact(new Contact()
                                .name("Dana Valentina Bernal León")
                                .email("danabernal222@unisangil.edu.co")
                                .url("https://beauty.com")));
    }
}
