package nl.kristalsoftware.association.member.datastore.types.member.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.datastore.base.eventstore.event.message.EventMessageHandler;
import nl.kristalsoftware.datastore.base.eventstore.event.message.EventMessageHandlerProvider;
import nl.kristalsoftware.datastore.base.messaging.EventConsumer;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberMessageConsumer extends EventConsumer<MemberEventData> {

    private final EventMessageHandlerProvider eventMessageHandlerProvider;

    @Transactional
//    @KafkaListener(topics = "${member.kafka.member.topicname}") //, containerFactory = "playerKafkaListenerContainerFactory")
    public void consumeData(@Payload ConsumerRecord<String, MemberEventData> record) {
        log.info("Member: Key: {}, Value: {}, Partition: {}, Offset: {}",
                record.partition(), record.offset(), record.key(), record.value());
        EventMessageHandler eventMessageHandler = eventMessageHandlerProvider.getEventMessageHandler(record.value().getDomainEventName());
        if (eventMessageHandler != null) {
            super.consumeData(eventMessageHandler, record);
        }
        else {
            throw new IllegalStateException("No eventMessageHandler found!");
        }
    }

}
