package nl.kristalsoftware.association.member.autoconfiguration;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(DomainConfiguration.class)
public class DomainAutoConfiguration {
}
