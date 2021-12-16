package nl.kristalsoftware.association.member.datastore.types.address.eventstore.event;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.datastore.types.address.eventstore.AddressEventStoreRepository;
import nl.kristalsoftware.association.member.domain.address.properties.AddressReference;
import nl.kristalsoftware.datastore.base.eventstore.event.BaseEventFinder;
import nl.kristalsoftware.domain.base.Aggregate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddressReferenceEventFinder<T extends Aggregate<? extends AddressReference>> implements BaseEventFinder<T,AddressBaseEventEntity> {

    private AddressEventStoreRepository eventStoreRepository;

    @Autowired
    public void setEventStoreRepository(AddressEventStoreRepository eventStoreRepository) {
        this.eventStoreRepository = eventStoreRepository;
    }

    public List<AddressBaseEventEntity> getEvents(T aggregateRoot) {
        return StreamSupport.stream(
                        findAllEventEntitiesByReference(aggregateRoot).spliterator(), false)
                .collect(Collectors.toList());
    }

    public Iterable<AddressBaseEventEntity> findAllEventEntitiesByReference(T aggregateRoot) {
        return eventStoreRepository.findAllByZipCodeAndStreetNumber(
                aggregateRoot.getReference().getZipCode().getValue(),
                aggregateRoot.getReference().getStreetNumber().getValue()
        );
    }

}
