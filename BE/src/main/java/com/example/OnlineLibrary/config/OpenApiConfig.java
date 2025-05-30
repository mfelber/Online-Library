package com.example.OnlineLibrary.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
    info = @Info(
        contact = @Contact(name = "Marcel,", email = "test@gmail.com", url = "https://google.com"),
        description = "OpenApi documentation",
        title = "OpenApi specification",
        version = "1.0",
        license = @License(name = "License name", url = "https://some-url.com"),
        termsOfService = "Terms of service"),
    servers = {
        @Server(description = "Local environment", url = "http://localhost:8088/api/v1"),
        @Server(description = "Production environment", url = "https://api.example.com/v1")
    },
    security = @SecurityRequirement(
        name = "bearerAuth"
    )
)
@SecurityScheme(
    name = "bearerAuth",
    description = "JWT auth",
    scheme = "bearer",
    type = SecuritySchemeType.HTTP,
    bearerFormat = "JWT",
    in = SecuritySchemeIn.HEADER
)
public class OpenApiConfig {

}
