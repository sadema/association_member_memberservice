package nl.kristalsoftware.association.member.autoconfiguration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ComponentScan(basePackages = "nl.kristalsoftware.association.member.rest")
@Configuration
public class RestConfiguration {
}
