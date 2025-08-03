package com.movieflix.config;


import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
        name = "bearerAuth",
        type = SecuritySchemeType.HTTP,
        scheme = "bearer"
)
public class SwaggerConfig {
    @Bean
    //metodo para configurar a documentação da API
    // Retorna um objeto OpenAPI configurado com informações da API
    public OpenAPI getOpenAPI(){

        // Cria um objeto Contact com informações de contato do desenvolvedor
        Contact contact = new Contact();
        contact.name("Enzo");
        contact.email("enzosdev@gmail.com");
        // Cria um objeto Info com detalhes da API
        // Define o título, versão e descrição da API
       Info info = new Info();
        info.title("MovieFlix API");
        info.version("v1");
        info.description("API documentation for the MovieFlix application");
        info.contact(contact);


        return new OpenAPI()
                .info(info);
    }
}
