package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_address_unassigned;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.datastore.base.eventstore.event.entity.UUIDBaseEventEntity;

import javax.persistence.Entity;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "MemberAddressUnAssignedEvent")
public class MemberAddressUnAssignedEventEntity extends UUIDBaseEventEntity {

    private String zipCode;

    private String streetNumber;

    private MemberAddressUnAssignedEventEntity(
            UUID reference,
            String domainEventName,
            String zipCode,
            String streetNumber
    ) {
        super(reference, domainEventName);
        this.zipCode = zipCode;
        this.streetNumber = streetNumber;
    }

    public static MemberAddressUnAssignedEventEntity of(MemberEventData memberEventData) {
        return new MemberAddressUnAssignedEventEntity(
                memberEventData.getReference(),
                memberEventData.getDomainEventName(),
                memberEventData.getAddress().getZipCode(),
                memberEventData.getAddress().getStreetNumber()
        );
    }

}
