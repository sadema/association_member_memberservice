package nl.kristalsoftware.association.member.datastore.types.address.eventstore;

import nl.kristalsoftware.association.member.datastore.types.address.eventstore.event.AddressBaseEventEntity;
import nl.kristalsoftware.association.member.datastore.types.address.eventstore.event.AddressEventFinder;
import nl.kristalsoftware.association.member.domain.address.Address;
import nl.kristalsoftware.association.member.domain.address.properties.AddressReference;
import nl.kristalsoftware.datastore.base.eventstore.BaseAggregateLoader;
import nl.kristalsoftware.domain.base.EventStore;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class AddressAggregateLoaderService extends BaseAggregateLoader<Address, AddressBaseEventEntity> implements EventStore<Address, AddressReference> {

    public AddressAggregateLoaderService(AddressEventFinder addressEventFinder) {
        super(addressEventFinder);
    }

    @Override
    public Address loadAggregate(AddressReference addressReference, ApplicationEventPublisher eventPublisher) {
        Address address = Address.of(addressReference, eventPublisher);
        loadEvents(address);
        return address;
    }

}
