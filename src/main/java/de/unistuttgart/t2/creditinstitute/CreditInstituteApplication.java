package de.unistuttgart.t2.creditinstitute;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * A dummy credit institute.
 * 
 * <p>
 * This credit institute provides a very fake payment.  
 * 
 * @author maumau
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
public class CreditInstituteApplication {

    public static void main(String[] args) {
        SpringApplication.run(CreditInstituteApplication.class, args);
    }

    @Bean
    public CreditInstituteService service() {
        return new CreditInstituteService();
    }
}
