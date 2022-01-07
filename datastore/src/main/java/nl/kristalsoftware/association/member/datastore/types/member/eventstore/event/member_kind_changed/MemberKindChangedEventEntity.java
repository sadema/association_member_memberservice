package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_kind_changed;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_signed_up.AbstractUUIDBaseEventEntity;
import nl.kristalsoftware.association.member.domain.member.CurrentMemberState;
import nl.kristalsoftware.association.member.domain.member.event.member_kind_changed.MemberKindChanged;
import nl.kristalsoftware.association.member.domain.member.properties.Kind;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "MemberKindChangedEvent")
public class MemberKindChangedEventEntity extends AbstractUUIDBaseEventEntity<MemberKindChangedEventEntity> {

    @Enumerated(EnumType.STRING)
    private Kind kind;

    private void registerPublicDomainEvent(CurrentMemberState currentMemberState) {
        MemberEventData memberEventData = MemberEventData.newBuilder()
                .setDomainEventName(getDomainEventName())
                .setReference(getReference())
                .setFirstName(currentMemberState.getMemberName().getFirstName())
                .setLastName(currentMemberState.getMemberName().getLastName())
                .setBirthDate(currentMemberState.getMemberBirthDate().getInstant())
                .setKind(kind.name())
                .build();
        registerEvent(memberEventData);
    }

    private MemberKindChangedEventEntity(
            UUID reference,
            String domainEventName,
            Kind kind
    ) {
        super(reference, domainEventName);
        this.kind = kind;
    }

    public static MemberKindChangedEventEntity of(MemberKindChanged memberKindChanged, CurrentMemberState currentState) {
        MemberKindChangedEventEntity memberKindChangedEventEntity = new MemberKindChangedEventEntity(
                memberKindChanged.getMemberReference().getValue(),
                memberKindChanged.getClass().getSimpleName(),
                Kind.valueOf(memberKindChanged.getMemberKind().getValue().name())
        );
        memberKindChangedEventEntity.registerPublicDomainEvent(currentState);
        return memberKindChangedEventEntity;
    }

}
