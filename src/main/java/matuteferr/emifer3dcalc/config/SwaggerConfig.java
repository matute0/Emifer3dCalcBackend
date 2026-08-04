package matuteferr.emifer3dcalc.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {
    @Value("${api.url}")
    private String apiUrl;
    @Bean
    public OpenAPI myOpenAPI(){
        Contact contact = new Contact();
        contact.setEmail("matifernandezescuder@hotmail.com");
        contact.setName("matute0");
        contact.setUrl("https://matute0.vercel.app/");

        Server apiServer = new Server();
        apiServer.setUrl(apiUrl);
        apiServer.setDescription("API Server");

        Info info = new Info()
                .title("API Emifer3dCalc")
                .version("1.0")
                .contact(contact);

        return new OpenAPI().info(info).servers(List.of(apiServer));
    }
}
