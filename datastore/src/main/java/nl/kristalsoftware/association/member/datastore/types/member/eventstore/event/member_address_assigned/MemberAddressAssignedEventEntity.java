package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_address_assigned;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.datastore.base.eventstore.event.entity.UUIDBaseEventEntity;

import javax.persistence.Entity;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "MemberAddressAssignedEvent")
public class MemberAddressAssignedEventEntity extends UUIDBaseEventEntity {

    private String zipCode;

    private String streetNumber;

    private MemberAddressAssignedEventEntity(
            UUID reference,
            String domainEventName,
            String zipCode,
            String streetNumber
    ) {
        super(reference, domainEventName);
        this.zipCode = zipCode;
        this.streetNumber = streetNumber;
    }

    public static MemberAddressAssignedEventEntity of(MemberEventData memberEventData) {
        return new MemberAddressAssignedEventEntity(
                memberEventData.getReference(),
                memberEventData.getDomainEventName(),
                memberEventData.getAddress().getZipCode(),
                memberEventData.getAddress().getStreetNumber()
        );
    }

}
