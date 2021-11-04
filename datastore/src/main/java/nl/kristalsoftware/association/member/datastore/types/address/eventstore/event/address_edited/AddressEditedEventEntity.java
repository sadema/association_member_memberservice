package nl.kristalsoftware.association.member.datastore.types.address.eventstore.event.address_edited;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.AddressEventData;
import nl.kristalsoftware.datastore.base.eventstore.event.entity.BaseEventEntity;

import javax.persistence.Entity;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "AddressEditedEvent")
public class AddressEditedEventEntity extends BaseEventEntity {

    private String street;

    private String city;

    private String zipCode;

    private AddressEditedEventEntity(
            UUID reference,
            String domainEventName,
            String street,
            String city,
            String zipCode
    ) {
        super(reference, domainEventName);
        this.street = street;
        this.city = city;
        this.zipCode = zipCode;
    }

    public static AddressEditedEventEntity of(AddressEventData addressEventData) {
        AddressEditedEventEntity entity = new AddressEditedEventEntity(
                addressEventData.getReference(),
                addressEventData.getDomainEventName(),
                addressEventData.getStreet(),
                addressEventData.getCity(),
                addressEventData.getZip()
        );
        return entity;
    }

}
