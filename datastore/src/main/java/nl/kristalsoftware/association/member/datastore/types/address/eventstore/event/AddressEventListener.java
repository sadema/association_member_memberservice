package nl.kristalsoftware.association.member.datastore.types.address.eventstore.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.AddressDetails;
import nl.kristalsoftware.association.member.AddressEventData;
import nl.kristalsoftware.association.member.datastore.types.address.messaging.AddressEventDataProducer;
import nl.kristalsoftware.association.member.domain.address.event.AddressEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AddressEventListener {

    private final AddressEventDataProducer addressEventDataProducer;

    @EventListener
    public void onApplicationEvent(AddressEvent event) {
        AddressEventData addressEventData = AddressEventData.newBuilder()
                .setDomainEventName(event.getDomainEventName().name())
                .setAddress(AddressDetails.newBuilder()
                        .setZipCode(event.getAddress().getZipCode().getValue())
                        .setStreetNumber(event.getAddress().getStreetNumber().getValue())
                        .setStreet(event.getAddress().getStreet().getValue())
                        .setCity(event.getAddress().getCity().getValue())
                        .build()
                )
                .build();
        addressEventDataProducer.produce(addressEventData);
    }

}
