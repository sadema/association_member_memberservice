package nl.kristalsoftware.association.member.datastore.types.address.messaging;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.AddressEventData;
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
public class AddressMessageConsumer extends EventConsumer<AddressEventData> {

    private final EventMessageHandlerProvider eventMessageHandlerProvider;

    @Transactional
//    @KafkaListener(topics = "${member.kafka.address.topicname}") //, containerFactory = "playerKafkaListenerContainerFactory")
    public void consumeData(@Payload ConsumerRecord<String, AddressEventData> record) {
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
