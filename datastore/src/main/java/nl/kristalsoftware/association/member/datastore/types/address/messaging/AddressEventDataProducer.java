package nl.kristalsoftware.association.member.datastore.types.address.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.AddressEventData;
import nl.kristalsoftware.datastore.base.messaging.GenericEventProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AddressEventDataProducer {

    private final GenericEventProducer<AddressEventData> eventProducer;

    private final KafkaTemplate<String, AddressEventData> kafkaTemplate;

    @Value("${member.kafka.address.topicname}")
    private String topicname;

    public void produce(AddressEventData addressEventData) {
        eventProducer.produceEvent(
                kafkaTemplate,
                topicname,
                addressEventData.getAddress().getZipCode() + "," + addressEventData.getAddress().getStreetNumber(),
                addressEventData);
    }

}
