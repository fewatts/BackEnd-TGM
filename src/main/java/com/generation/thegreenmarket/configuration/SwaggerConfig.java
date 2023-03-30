package com.generation.thegreenmarket.configuration;

import org.springdoc.core.customizers.OpenApiCustomiser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.responses.ApiResponse;
import io.swagger.v3.oas.models.responses.ApiResponses;

@Configuration
public class SwaggerConfig {

	@Bean
	public OpenAPI springBlogPessoalOpenAPI() {
		return new OpenAPI()
				.info(new Info()
					.title("Projeto The Green Market")
					.description("Projeto The Green Market - ")
					.version("v0.0.1")
				.license(new License()
					.name("Generation Brasil")
					.url("https://brazil.generation.org/"))
				.contact(new Contact()
					.name("Erica Araújo")
					.url("https://github.com/EricaArj")
					.email("erika.f55@hotmail.com"))
                .contact(new Contact()
                    .name("Fernando Alves")
                    .url("https://github.com/fewatts")
                    .email("feralveswatts@gmail.com"))
                .contact(new Contact()
                    .name("Giovana Oliveira")
                    .url("https://github.com/macgii")
                    .email("maiiaraoliveiras1@gmail.com"))
                .contact(new Contact()
                    .name("Isaac Castanho")
                    .url("https://github.com/Isaac-MCastanho")
                    .email("fgcastanho@hotmail.com"))
                .contact(new Contact()
                    .name("Katiana Xavier")
                    .url("https://github.com/KatianaXavier")
                    .email("katianaxavierb@gmail.com"))
                .contact(new Contact()
                    .name("Luan Silva")
                    .url("https://github.com/LuanSilva94")
                    .email("ss.luan21@gmail.com")))
				.externalDocs(new ExternalDocumentation()
					.description("Github do projeto")
					.url("https://github.com/fewatts/The_Green_market"));
	}

	@Bean
	public OpenApiCustomiser customerGlobalHeaderOpenApiCustomiser() {

		return openApi -> {
			openApi.getPaths().values().forEach(pathItem -> pathItem.readOperations().forEach(operation -> {

				ApiResponses apiResponses = operation.getResponses();

				apiResponses.addApiResponse("200", createApiResponse("Sucesso!"));
				apiResponses.addApiResponse("201", createApiResponse("Objeto Persistido!"));
				apiResponses.addApiResponse("204", createApiResponse("Objeto Excluído!"));
				apiResponses.addApiResponse("400", createApiResponse("Erro na Requisição!"));
				apiResponses.addApiResponse("401", createApiResponse("Acesso Não Autorizado!"));
				apiResponses.addApiResponse("404", createApiResponse("Objeto Não Encontrado!"));
				apiResponses.addApiResponse("500", createApiResponse("Erro na Aplicação!"));

			}));
		};
	}

	private ApiResponse createApiResponse(String message) {

		return new ApiResponse().description(message);

	}
	
}
