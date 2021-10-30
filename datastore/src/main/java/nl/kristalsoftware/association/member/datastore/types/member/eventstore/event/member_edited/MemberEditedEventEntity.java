package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_edited;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.datastore.base.eventstore.event.entity.BaseEventEntity;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "MemberEditedEvent")
public class MemberEditedEventEntity extends BaseEventEntity {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private MemberEditedEventEntity(
            UUID reference,
            String domainEventName,
            String firstName,
            String lastName,
            LocalDate birthDate
    ) {
        super(reference, domainEventName);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public static MemberEditedEventEntity of(MemberEventData memberEventData) {
        return new MemberEditedEventEntity(
                UUID.fromString(memberEventData.getReference()),
                memberEventData.getDomainEventName(),
                memberEventData.getFirstName(),
                memberEventData.getLastName(),
                BaseEventEntity.getLocalDateFromMillis(memberEventData.getBirthDate())
        );
    }

}
