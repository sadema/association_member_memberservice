package nl.kristalsoftware.association.member.datastore.types.address.eventstore.event.address_added;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.AddressEventData;
import nl.kristalsoftware.association.member.datastore.types.address.eventstore.event.AddressBaseEventEntity;

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

    public static AddressAddedEventEntity of(AddressEventData addressEventData) {
        AddressAddedEventEntity entity = new AddressAddedEventEntity(
                addressEventData.getAddress().getZipCode(),
                addressEventData.getAddress().getStreetNumber(),
                addressEventData.getDomainEventName(),
                addressEventData.getAddress().getStreet(),
                addressEventData.getAddress().getCity()
        );
        return entity;
    }

}
