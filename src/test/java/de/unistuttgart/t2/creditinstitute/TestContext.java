package de.unistuttgart.t2.creditinstitute;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import io.opentracing.contrib.java.spring.jaeger.starter.JaegerAutoConfiguration;
import io.opentracing.contrib.spring.web.starter.ServerTracingAutoConfiguration;

@Configuration
@EnableAutoConfiguration(exclude = {ServerTracingAutoConfiguration.class, JaegerAutoConfiguration.class})
@Profile("test")
public class TestContext {
    @Bean
    public CreditInstituteService service() {
        return new CreditInstituteService();
    }
}
