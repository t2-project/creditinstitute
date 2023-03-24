package de.unistuttgart.t2.creditinstitute;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.sleuth.autoconfig.zipkin2.ZipkinAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableAutoConfiguration(exclude = {ZipkinAutoConfiguration.class})
@Profile("test")
public class TestContext {

    @Bean
    public CreditInstituteService service() {
        return new CreditInstituteService();
    }
}
