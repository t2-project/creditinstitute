package de.unistuttgart.t2.creditinstitute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import de.unistuttgart.t2.common.BaseScan;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * A dummy credit institute.
 * <p>
 * This credit institute provides a very fake payment.
 * 
 * @author maumau
 */
@EnableAutoConfiguration
@SpringBootApplication(scanBasePackageClasses = BaseScan.class)
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
            .description("API of the T2 Store's creditinstitute service."));
    }
}
