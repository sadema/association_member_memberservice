package nl.kristalsoftware.association.member.datastore.types.address.eventstore.event.address_edited;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.AddressEventData;
import nl.kristalsoftware.association.member.datastore.types.address.eventstore.event.AddressBaseEventEntity;

import javax.persistence.Entity;

@NoArgsConstructor
@Data
@Entity(name = "AddressEditedEvent")
public class AddressEditedEventEntity extends AddressBaseEventEntity {

    private String street;

    private String city;

    private AddressEditedEventEntity(
            String zipCode,
            String streetNumber,
            String domainEventName,
            String street,
            String city
    ) {
        super(zipCode, streetNumber, domainEventName);
        this.street = street;
        this.city = city;
    }

    public static AddressEditedEventEntity of(AddressEventData addressEventData) {
        AddressEditedEventEntity entity = new AddressEditedEventEntity(
                addressEventData.getAddress().getZipCode(),
                addressEventData.getAddress().getStreetNumber(),
                addressEventData.getDomainEventName(),
                addressEventData.getAddress().getStreet(),
                addressEventData.getAddress().getCity()
        );
        return entity;
    }

}
