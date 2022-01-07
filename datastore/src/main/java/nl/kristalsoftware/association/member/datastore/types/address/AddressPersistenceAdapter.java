package nl.kristalsoftware.association.member.datastore.types.address;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.address.Address;
import nl.kristalsoftware.datastore.base.eventstore.event.EventSaveHandler;
import nl.kristalsoftware.datastore.base.eventstore.event.entity.EventHandlerProvider;
import nl.kristalsoftware.domain.base.BaseEvent;
import nl.kristalsoftware.domain.base.PersistenceHandlerPort;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AddressPersistenceAdapter implements PersistenceHandlerPort<Address> {

    private final EventHandlerProvider eventHandlerProvider;

    public <T extends BaseEvent> void save(Address member, T domainEvent) {
        EventSaveHandler eventHandler = eventHandlerProvider.getEventSaveHandler(domainEvent.getClass().getSimpleName());
        eventHandler.save(member, domainEvent);
    }

}
