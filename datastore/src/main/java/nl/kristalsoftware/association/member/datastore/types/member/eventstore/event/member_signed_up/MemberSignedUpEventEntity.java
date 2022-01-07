package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_signed_up;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberSignedUp;
import nl.kristalsoftware.association.member.domain.member.properties.Kind;
import nl.kristalsoftware.domain.base.TinyDateType;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "MemberSignedUpEvent")
public class MemberSignedUpEventEntity extends AbstractUUIDBaseEventEntity<MemberSignedUpEventEntity> {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    @Enumerated(EnumType.STRING)
    private Kind kind;

    private void registerPublicDomainEvent() {
        MemberEventData memberEventData = MemberEventData.newBuilder()
                .setDomainEventName(getDomainEventName())
                .setReference(getReference())
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBirthDate(TinyDateType.getInstantFromLocalDate(birthDate))
                .setKind(kind.name())
                .build();
        registerEvent(memberEventData);
    }

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

    public static MemberSignedUpEventEntity of(MemberSignedUp memberSignedUp) {
        MemberSignedUpEventEntity memberSignedUpEventEntity = new MemberSignedUpEventEntity(
                memberSignedUp.getMemberReference().getValue(),
                memberSignedUp.getDomainEventName().name(),
                memberSignedUp.getMemberName().getFirstName(),
                memberSignedUp.getMemberName().getLastName(),
                TinyDateType.getLocalDateFromMillis(memberSignedUp.getMemberBirthDate().getDateInMillis()),
                memberSignedUp.getMemberKind().getValue()
        );
        memberSignedUpEventEntity.registerPublicDomainEvent();
        return memberSignedUpEventEntity;
    }

}
