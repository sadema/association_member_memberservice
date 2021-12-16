package nl.kristalsoftware.association.member.datastore.types.address.eventstore.event.address_added;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.AddressEventData;
import nl.kristalsoftware.association.member.datastore.types.address.eventstore.AddressEventStoreRepository;
import nl.kristalsoftware.association.member.domain.address.Address;
import nl.kristalsoftware.association.member.domain.address.event.event_types.AddressAdded;
import nl.kristalsoftware.association.member.domain.address.properties.City;
import nl.kristalsoftware.association.member.domain.address.properties.Street;
import nl.kristalsoftware.association.member.domain.address.properties.StreetNumber;
import nl.kristalsoftware.association.member.domain.address.properties.ZipCode;
import nl.kristalsoftware.datastore.base.eventstore.event.EventHandler;
import nl.kristalsoftware.datastore.base.eventstore.event.message.EventMessageHandler;
import nl.kristalsoftware.domain.base.BaseEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class AddressAddedHandler implements EventHandler<Address, AddressAddedEventEntity>, EventMessageHandler<AddressEventData> {

    private final AddressEventStoreRepository eventStoreRepository;

    @Override
    public Class<? extends BaseEvent> appliesTo() {
        return AddressAdded.class;
    }

    @Override
    @Transactional
    public void save(AddressEventData addressEventData) {
        eventStoreRepository.save(AddressAddedEventEntity.of(addressEventData));
    }

    @Override
    public void loadEventData(Address address, AddressAddedEventEntity eventEntity) {
        log.info("AddressAddedEventEntity: {} {} {}", eventEntity.getZipCode(), eventEntity.getStreetNumber(), eventEntity.getDomainEventName());
        AddressAdded addressAssigned = AddressAdded.of(
                ZipCode.of(eventEntity.getZipCode()),
                StreetNumber.of(eventEntity.getStreetNumber()),
                Street.of(eventEntity.getStreet()),
                City.of(eventEntity.getCity())
        );
        address.loadData(addressAssigned);
    }

}
