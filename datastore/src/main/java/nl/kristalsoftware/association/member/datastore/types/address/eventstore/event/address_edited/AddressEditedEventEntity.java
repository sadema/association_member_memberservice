package nl.kristalsoftware.association.member.datastore.types.address.eventstore.event.address_edited;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.datastore.types.address.eventstore.event.AddressBaseEventEntity;
import nl.kristalsoftware.association.member.domain.address.event.event_types.AddressEdited;

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

    public static AddressEditedEventEntity of(AddressEdited addressEdited) {
        AddressEditedEventEntity entity = new AddressEditedEventEntity(
                addressEdited.getZipCode().getValue(),
                addressEdited.getStreetNumber().getValue(),
                addressEdited.getClass().getSimpleName(),
                addressEdited.getStreet().getValue(),
                addressEdited.getCity().getValue()
        );
        return entity;
    }

}
