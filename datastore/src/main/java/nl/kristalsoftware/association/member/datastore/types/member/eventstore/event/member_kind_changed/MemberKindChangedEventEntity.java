package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_kind_changed;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.domain.member.properties.Kind;
import nl.kristalsoftware.datastore.base.eventstore.event.entity.UUIDBaseEventEntity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "MemberKindChangedEvent")
public class MemberKindChangedEventEntity extends UUIDBaseEventEntity {

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
                memberEventData.getReference(),
                memberEventData.getDomainEventName(),
                Kind.valueOf(memberEventData.getKind())
        );
    }

}
