package com.desafioapilabs.config;





import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.OAuthFlow;
import io.swagger.v3.oas.models.security.OAuthFlows;
import io.swagger.v3.oas.models.security.SecurityScheme;


@Configuration
public class OpenApiConfig {

	@Bean
	public OpenAPI customConfiguration() {
		return new OpenAPI()
				.components(
					new Components()
					.addSecuritySchemes("oAuthScheme", 
							new SecurityScheme()
							.type(SecurityScheme.Type.OAUTH2)
							.description("This API uses OAuth 2 with the implicit grant flow. [More info](https://api.example.com/docs/auth)")
							.flows(new OAuthFlows()
									.implicit(new OAuthFlow()
											//.tokenUrl("http://localhost:8080/oauth/token")
											.authorizationUrl("http://localhost:8080/oauth/token")
											
							 ))
					)
				)
				.info(new Info().title("ApiLabs API Docs")
						.description("Documentação da API"));
		
				
				
	}
	

}
