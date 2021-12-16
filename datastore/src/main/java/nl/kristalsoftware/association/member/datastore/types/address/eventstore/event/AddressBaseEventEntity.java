package nl.kristalsoftware.association.member.datastore.types.address.eventstore.event;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.kristalsoftware.datastore.base.eventstore.event.entity.BaseEventEntityName;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@NoArgsConstructor
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AddressBaseEventEntity implements BaseEventEntityName {

    @Setter(AccessLevel.NONE)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String zipCode;

    private String streetNumber;

    private String domainEventName;

    public AddressBaseEventEntity(String zipCode, String streetNumber, String domainEventName) {
        this.zipCode = zipCode;
        this.streetNumber = streetNumber;
        this.domainEventName = domainEventName;
    }

}
