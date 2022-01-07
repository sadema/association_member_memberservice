package nl.kristalsoftware.association.member.datastore.types.address.eventstore;

import nl.kristalsoftware.association.member.datastore.types.address.eventstore.event.AddressBaseEventEntity;
import nl.kristalsoftware.association.member.datastore.types.address.eventstore.event.AddressEventFinder;
import nl.kristalsoftware.association.member.domain.address.Address;
import nl.kristalsoftware.association.member.domain.address.properties.AddressReference;
import nl.kristalsoftware.datastore.base.eventstore.BaseAggregateLoader;
import nl.kristalsoftware.domain.base.EventStore;
import nl.kristalsoftware.domain.base.PersistenceHandlerPort;
import org.springframework.stereotype.Component;

@Component
public class AddressAggregateLoaderService extends BaseAggregateLoader<Address, AddressBaseEventEntity> implements EventStore<Address, AddressReference> {

    public AddressAggregateLoaderService(AddressEventFinder addressEventFinder) {
        super(addressEventFinder);
    }

    @Override
    public Address loadAggregate(AddressReference addressReference, PersistenceHandlerPort<Address> persistenceHandler) {
        Address address = Address.of(addressReference, persistenceHandler);
        loadEvents(address);
        return address;
    }

}
