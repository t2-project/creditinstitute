package de.unistuttgart.t2.creditinstitute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.*;
import io.swagger.v3.oas.models.info.Info;

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
    public OpenAPI customOpenAPI() {
        return new OpenAPI().components(new Components()).info(new Info().title("Creditinstitute service API")
            .description("API of the T2-Project's creditinstitute service."));
    }
}
