package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_kind_changed;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.domain.member.properties.Kind;
import nl.kristalsoftware.datastore.base.eventstore.event.entity.BaseEventEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "MemberStateChangedEvent")
public class MemberKindChangedEventEntity extends BaseEventEntity {

    @Enumerated(EnumType.STRING)
    private Kind kind;

    private MemberKindChangedEventEntity(
            UUID reference,
            String domainEventName,
            Kind kind
    ) {
        super(reference, domainEventName);
        this.kind = kind;
    }

    public static MemberKindChangedEventEntity of(MemberEventData memberEventData) {
        return new MemberKindChangedEventEntity(
                UUID.fromString(memberEventData.getReference()),
                memberEventData.getDomainEventName(),
                Kind.valueOf(memberEventData.getKind())
        );
    }

}
