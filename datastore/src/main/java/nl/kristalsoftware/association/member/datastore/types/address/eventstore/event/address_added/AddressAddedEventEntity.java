package nl.kristalsoftware.association.member.datastore.types.address.eventstore.event.address_added;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.datastore.types.address.eventstore.event.AddressBaseEventEntity;
import nl.kristalsoftware.association.member.domain.address.event.event_types.AddressAdded;

import javax.persistence.Entity;

@NoArgsConstructor
@Data
//@Table(uniqueConstraints = {
//        @UniqueConstraint(
//                name = "UniqueZipCodeAndStreetNumber",
//                columnNames = {"zipCode", "streetNumber"}
//        )
//})
@Entity(name = "AddressAddedEvent")
public class AddressAddedEventEntity extends AddressBaseEventEntity {

    private String street;

    private String city;

    private AddressAddedEventEntity(
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

    public static AddressAddedEventEntity of(AddressAdded addressAdded) {
        AddressAddedEventEntity entity = new AddressAddedEventEntity(
                addressAdded.getZipCode().getValue(),
                addressAdded.getStreetNumber().getValue(),
                addressAdded.getClass().getSimpleName(),
                addressAdded.getStreet().getValue(),
                addressAdded.getCity().getValue()
        );
        return entity;
    }

}
