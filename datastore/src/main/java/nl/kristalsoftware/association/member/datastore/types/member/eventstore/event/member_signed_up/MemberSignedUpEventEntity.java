package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_signed_up;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.datastore.base.eventstore.event.entity.BaseEventEntity;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "MemberSignedUpEvent")
public class MemberSignedUpEventEntity extends BaseEventEntity {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private String address;

    private String city;

    private String zipCode;

    private MemberSignedUpEventEntity(
            UUID reference,
            String domainEventName,
            String firstName,
            String lastName,
            String address,
            String city,
            String zipCode
    ) {
        super(reference, domainEventName);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.address = address;
        this.city = city;
        this.zipCode = zipCode;
    }

    public static MemberSignedUpEventEntity of(MemberEventData memberEventData) {
        MemberSignedUpEventEntity entity = new MemberSignedUpEventEntity(
                UUID.fromString(memberEventData.getReference()),
                memberEventData.getDomainEventName(),
                memberEventData.getFirstName(),
                memberEventData.getLastName(),
                memberEventData.getAddress(),
                memberEventData.getCity(),
                memberEventData.getZip()
        );
        entity.birthDate = entity.getLocalDateFromMillis(memberEventData.getBirthDate());
        return entity;
    }

}
