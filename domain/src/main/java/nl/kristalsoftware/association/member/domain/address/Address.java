package nl.kristalsoftware.association.member.domain.address;

import lombok.Getter;
import nl.kristalsoftware.association.member.domain.address.command.AddAddress;
import nl.kristalsoftware.association.member.domain.address.command.EditAddress;
import nl.kristalsoftware.association.member.domain.address.command.QuitAddress;
import nl.kristalsoftware.association.member.domain.address.event.AddressEvent;
import nl.kristalsoftware.association.member.domain.address.event.AddressEventDefinition;
import nl.kristalsoftware.association.member.domain.address.event.event_types.AddressAdded;
import nl.kristalsoftware.association.member.domain.address.event.event_types.AddressEdited;
import nl.kristalsoftware.association.member.domain.address.event.event_types.AddressQuited;
import nl.kristalsoftware.association.member.domain.address.properties.AddressReference;
import nl.kristalsoftware.association.member.domain.address.properties.City;
import nl.kristalsoftware.association.member.domain.address.properties.Street;
import nl.kristalsoftware.association.member.domain.address.properties.StreetNumber;
import nl.kristalsoftware.association.member.domain.address.properties.ZipCode;
import nl.kristalsoftware.association.member.domain.member.properties.MemberReference;
import nl.kristalsoftware.domain.base.Aggregate;
import nl.kristalsoftware.domain.base.BaseAggregateRoot;
import nl.kristalsoftware.domain.base.annotations.AggregateRoot;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

@Getter
@AggregateRoot
public class Address extends BaseAggregateRoot<AddressReference, AddressEvent> implements Aggregate {

    private Street street;

    private StreetNumber streetNumber;

    private City city;

    private ZipCode zipCode;

    private List<MemberReference> memberReferences;

//    private List<PersonRole> personRoleList;

    private Address(AddressReference reference, ApplicationEventPublisher eventPublisher) {
        super(reference, eventPublisher);
    }

    public static Address of(AddressReference reference, ApplicationEventPublisher eventPublisher) {
        return new Address(reference, eventPublisher);
    }

    public void loadData(AddressAdded domainEvent) {
        street = domainEvent.getStreet();
        streetNumber = domainEvent.getStreetNumber();
        city = domainEvent.getCity();
        zipCode = domainEvent.getZipCode();
    }

    public void loadData(AddressEdited domainEvent) {
        street = domainEvent.getStreet();
        streetNumber = domainEvent.getStreetNumber();
        city = domainEvent.getCity();
        zipCode = domainEvent.getZipCode();
    }

    public void loadData(AddressQuited domainEvent) {
        // TODO: do something
    }

    public void handleCommand(AddAddress command) {
        sendEvent(AddressEvent.of(
                getReference(),
                AddressEventDefinition.AddressAdded,
                command.getStreet(),
                command.getStreetNumber(),
                command.getCity(),
                command.getZipCode()
        ));
    }

    public void handleCommand(EditAddress command) {
        sendEvent(AddressEvent.of(
                getReference(),
                AddressEventDefinition.AddressEdited,
                command.getStreet(),
                command.getStreetNumber(),
                command.getCity(),
                command.getZipCode()
        ));
    }

    public void handleCommand(QuitAddress command) {
        sendEvent(AddressEvent.of(
                getReference(),
                AddressEventDefinition.AddressQuited,
                this.street,
                this.streetNumber,
                this.city,
                this.zipCode
        ));
    }
}
