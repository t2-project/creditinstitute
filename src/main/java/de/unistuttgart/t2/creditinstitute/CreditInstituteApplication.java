package de.unistuttgart.t2.creditinstitute;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * A dummy credit institute.
 * <p>
 * This credit institute provides a very fake payment.
 *
 * @author maumau
 */
@SpringBootApplication
public class CreditInstituteApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditInstituteApplication.class, args);
    }

    @Bean
    public CreditInstituteService service() {
        return new CreditInstituteService();
    }

    @Bean
    public OpenAPI customOpenAPI(@Value("${info.app.version:unknown}") String version) {
        return new OpenAPI().components(new Components()).info(new Info()
            .title("T2 Creditinstitute service API")
            .description("API of the T2-Project's creditinstitute service.")
            .version(version));
    }
}
