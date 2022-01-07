package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_quited;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberQuited;
import nl.kristalsoftware.datastore.base.eventstore.event.entity.UUIDBaseEventEntity;

import javax.persistence.Entity;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity(name = "MemberQuitedEvent")
public class MemberQuitedEventEntity extends UUIDBaseEventEntity {

    private MemberQuitedEventEntity(
            UUID reference,
            String domainEventName
    ) {
        super(reference, domainEventName);
    }

    public static MemberQuitedEventEntity of(MemberQuited memberQuited) {
        return new MemberQuitedEventEntity(
                memberQuited.getMemberReference().getValue(),
                memberQuited.getClass().getSimpleName()
        );
    }

}
