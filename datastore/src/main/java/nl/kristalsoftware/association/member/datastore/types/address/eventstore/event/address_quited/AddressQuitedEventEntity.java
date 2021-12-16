package nl.kristalsoftware.association.member.datastore.types.address.eventstore.event.address_quited;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.AddressEventData;
import nl.kristalsoftware.association.member.datastore.types.address.eventstore.event.AddressBaseEventEntity;

import javax.persistence.Entity;

@NoArgsConstructor
@Data
@Entity(name = "AddressQuitedEvent")
public class AddressQuitedEventEntity extends AddressBaseEventEntity {

    private AddressQuitedEventEntity(
            String zipCode,
            String streetNumber,
            String domainEventName
    ) {
        super(zipCode, streetNumber, domainEventName);
    }

    public static AddressQuitedEventEntity of(AddressEventData addressEventData) {
        AddressQuitedEventEntity entity = new AddressQuitedEventEntity(
                addressEventData.getAddress().getZipCode(),
                addressEventData.getAddress().getStreetNumber(),
                addressEventData.getDomainEventName()
        );
        return entity;
    }

}
