package nl.kristalsoftware.association.member.datastore.types.member.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.datastore.base.messaging.GenericEventProducer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberEventDataProducer {

    private final GenericEventProducer<MemberEventData> eventProducer;

    private final KafkaTemplate<String, MemberEventData> kafkaTemplate;

    @Value("${member.kafka.member.topicname}")
    private String topicname;

    public void produce(MemberEventData memberEventData) {
        eventProducer.produceEvent(
                kafkaTemplate,
                topicname,
                memberEventData.getReference().toString(),
                memberEventData);
    }

}
