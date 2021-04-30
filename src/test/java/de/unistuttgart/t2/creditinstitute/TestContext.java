package de.unistuttgart.t2.creditinstitute;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import de.unistuttgart.t2.creditinstitute.CreditInstituteController;
import de.unistuttgart.t2.creditinstitute.CreditInstituteService;

@Configuration
@EnableAutoConfiguration
@Profile("test")
public class TestContext {
    @Bean 
    public CreditInstituteService service() {
    	return new CreditInstituteService();
    }
}
