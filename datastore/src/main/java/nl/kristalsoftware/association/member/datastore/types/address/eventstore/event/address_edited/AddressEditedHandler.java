package nl.kristalsoftware.association.member.datastore.types.address.eventstore.event.address_edited;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.AddressEventData;
import nl.kristalsoftware.association.member.datastore.types.address.eventstore.AddressEventStoreRepository;
import nl.kristalsoftware.association.member.datastore.types.address.viewstore.AddressViewStore;
import nl.kristalsoftware.association.member.domain.address.Address;
import nl.kristalsoftware.association.member.domain.address.event.event_types.AddressEdited;
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
public class AddressEditedHandler implements EventHandler<Address, AddressEditedEventEntity>, EventMessageHandler<AddressEventData> {

    private final AddressEventStoreRepository eventStoreRepository;

    private final AddressViewStore addressViewStore;

    @Override
    public Class<? extends BaseEvent> appliesTo() {
        return AddressEdited.class;
    }

    @Override
    @Transactional
    public void save(AddressEventData addressEventData) {
        eventStoreRepository.save(AddressEditedEventEntity.of(addressEventData));
        addressViewStore.addressEdited(addressEventData);
    }

    @Override
    public void loadEventData(Address address, AddressEditedEventEntity eventEntity) {
        log.info("AddressEditedEventEntity: {} {} {}", eventEntity.getZipCode(), eventEntity.getStreetNumber(), eventEntity.getDomainEventName());
        AddressEdited addressEdited = AddressEdited.of(
                ZipCode.of(eventEntity.getZipCode()),
                StreetNumber.of(eventEntity.getStreetNumber()),
                Street.of(eventEntity.getStreet()),
                City.of(eventEntity.getCity())
        );
        address.loadData(addressEdited);
    }

}
