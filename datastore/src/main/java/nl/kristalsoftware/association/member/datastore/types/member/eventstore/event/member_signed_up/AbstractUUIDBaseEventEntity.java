package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_signed_up;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.kristalsoftware.datastore.base.eventstore.event.entity.BaseEventEntityName;
import org.hibernate.annotations.Type;
import org.springframework.data.domain.AbstractAggregateRoot;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.util.UUID;

@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AbstractUUIDBaseEventEntity<T extends AbstractAggregateRoot<T>> extends AbstractAggregateRoot<T> implements BaseEventEntityName {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Type(type = "uuid-char")
    private UUID reference;

    private String domainEventName;

    public AbstractUUIDBaseEventEntity(UUID reference, String domainEventName) {
        this.reference = reference;
        this.domainEventName = domainEventName;
    }

}
