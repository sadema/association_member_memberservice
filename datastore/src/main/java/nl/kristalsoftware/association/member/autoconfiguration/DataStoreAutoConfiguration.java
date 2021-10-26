package nl.kristalsoftware.association.member.autoconfiguration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Slf4j
@Configuration
@Import(DataStoreConfiguration.class)
public class DataStoreAutoConfiguration {
}
