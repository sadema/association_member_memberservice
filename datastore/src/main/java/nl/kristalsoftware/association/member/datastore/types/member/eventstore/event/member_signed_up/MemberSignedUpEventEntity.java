package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_signed_up;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.domain.member.properties.Kind;
import nl.kristalsoftware.datastore.base.eventstore.event.entity.UUIDBaseEventEntity;
import nl.kristalsoftware.domain.base.TinyDateType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "MemberSignedUpEvent")
public class MemberSignedUpEventEntity extends UUIDBaseEventEntity {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Kind kind;

    private MemberSignedUpEventEntity(
            UUID reference,
            String domainEventName,
            String firstName,
            String lastName,
            LocalDate birthDate,
            Kind kind
    ) {
        super(reference, domainEventName);
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.kind = kind;
    }

    public static MemberSignedUpEventEntity of(MemberEventData memberEventData) {
        return new MemberSignedUpEventEntity(
                memberEventData.getReference(),
                memberEventData.getDomainEventName(),
                memberEventData.getFirstName(),
                memberEventData.getLastName(),
                TinyDateType.getLocalDateFromInstant(memberEventData.getBirthDate()),
                Kind.valueOf(memberEventData.getKind())
        );
    }

}
