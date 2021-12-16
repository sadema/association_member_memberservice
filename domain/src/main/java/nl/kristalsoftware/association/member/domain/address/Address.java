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
import nl.kristalsoftware.association.member.domain.address.properties.CompoundAddress;
import nl.kristalsoftware.association.member.domain.address.properties.Street;
import nl.kristalsoftware.association.member.domain.member.properties.MemberReference;
import nl.kristalsoftware.domain.base.Aggregate;
import nl.kristalsoftware.domain.base.BaseAggregateRoot;
import nl.kristalsoftware.domain.base.annotations.AggregateRoot;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;

@Getter
@AggregateRoot
public class Address extends BaseAggregateRoot<AddressReference, AddressEvent> implements Aggregate<AddressReference> {

    private Street street;

    private City city;

    private List<MemberReference> memberReferences;

    private Address(AddressReference reference, ApplicationEventPublisher eventPublisher) {
        super(reference, eventPublisher);
    }

    public static Address of(AddressReference reference, ApplicationEventPublisher eventPublisher) {
        return new Address(reference, eventPublisher);
    }

    public void loadData(AddressAdded domainEvent) {
        street = domainEvent.getStreet();
        city = domainEvent.getCity();
    }

    public void loadData(AddressEdited domainEvent) {
        street = domainEvent.getStreet();
        city = domainEvent.getCity();
    }

    public void loadData(AddressQuited domainEvent) {
        // TODO: do something
    }

    public void handleCommand(AddAddress command) {
        sendEvent(AddressEvent.of(
                getReference(),
                AddressEventDefinition.AddressAdded,
                CompoundAddress.of(
                        getReference().getZipCode(),
                        getReference().getStreetNumber(),
                        command.getAddress().getStreet(),
                        command.getAddress().getCity()
                )
        ));
    }

    public Boolean handleCommand(EditAddress command) {
        if (command.getAddress().isEdited(street, city)) {
            sendEvent(AddressEvent.of(
                    getReference(),
                    AddressEventDefinition.AddressEdited,
                    CompoundAddress.of(
                            getReference().getZipCode(),
                            getReference().getStreetNumber(),
                            command.getAddress().getStreet(),
                            command.getAddress().getCity()
                    )
            ));
            return true;
        }
        return false;
    }

    public void handleCommand(QuitAddress command) {
//        sendEvent(AddressEvent.of(
//                getReference(),
//                AddressEventDefinition.AddressQuited,
//                CompoundAddress.of(
//                        getReference(),
//                        this.street,
//                        this.streetNumber,
//                        this.city,
//                        this.zipCode
//                )
//        ));
    }

}
