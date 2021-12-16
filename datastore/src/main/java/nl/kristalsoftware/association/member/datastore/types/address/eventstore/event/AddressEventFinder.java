package nl.kristalsoftware.association.member.datastore.types.address.eventstore.event;

import nl.kristalsoftware.association.member.domain.address.Address;
import org.springframework.stereotype.Component;

@Component
public class AddressEventFinder extends AddressReferenceEventFinder<Address> {
}
