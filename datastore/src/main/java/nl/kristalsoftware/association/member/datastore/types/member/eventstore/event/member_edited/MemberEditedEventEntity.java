package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_edited;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_signed_up.AbstractUUIDBaseEventEntity;
import nl.kristalsoftware.association.member.domain.member.CurrentMemberState;
import nl.kristalsoftware.association.member.domain.member.event.member_edited.MemberEdited;
import nl.kristalsoftware.domain.base.TinyDateType;

import javax.persistence.Entity;
import java.time.LocalDate;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "MemberEditedEvent")
public class MemberEditedEventEntity extends AbstractUUIDBaseEventEntity<MemberEditedEventEntity> {

    private String firstName;

    private String lastName;

    private LocalDate birthDate;

    private void registerPublicDomainEvent(CurrentMemberState stateBeforeEvent) {
        MemberEventData memberEventData = MemberEventData.newBuilder()
                .setDomainEventName(getDomainEventName())
                .setReference(getReference())
                .setFirstName(firstName)
                .setLastName(lastName)
                .setBirthDate(TinyDateType.getInstantFromLocalDate(birthDate))
                .setKind(stateBeforeEvent.getMemberKind().getValue().name())
                .build();
        registerEvent(memberEventData);
    }

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

    public static MemberEditedEventEntity of(MemberEdited memberEdited, CurrentMemberState currentState) {
        MemberEditedEventEntity memberEditedEventEntity = new MemberEditedEventEntity(
                memberEdited.getMemberReference().getValue(),
                memberEdited.getClass().getSimpleName(),
                memberEdited.getMemberName().getFirstName(),
                memberEdited.getMemberName().getLastName(),
                TinyDateType.getLocalDateFromMillis(memberEdited.getMemberBirthDate().getDateInMillis())
        );
        memberEditedEventEntity.registerPublicDomainEvent(currentState);
        return memberEditedEventEntity;
    }

}
