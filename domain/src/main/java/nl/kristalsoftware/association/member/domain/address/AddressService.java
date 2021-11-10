package nl.kristalsoftware.association.member.domain.address;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.address.command.AddAddress;
import nl.kristalsoftware.association.member.domain.address.command.EditAddress;
import nl.kristalsoftware.association.member.domain.address.command.QuitAddress;
import nl.kristalsoftware.association.member.domain.address.properties.AddressReference;
import nl.kristalsoftware.association.member.domain.address.properties.City;
import nl.kristalsoftware.association.member.domain.address.properties.Street;
import nl.kristalsoftware.association.member.domain.address.properties.StreetNumber;
import nl.kristalsoftware.association.member.domain.address.properties.ZipCode;
import nl.kristalsoftware.domain.base.EventStore;
import nl.kristalsoftware.domain.base.PropertiesNotChangedException;
import nl.kristalsoftware.domain.base.annotations.DomainService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@DomainService
@Service
public class AddressService {

    private final EventStore<Address, AddressReference> eventStore;

    private final ApplicationEventPublisher eventPublisher;

    private final AddressPersistencePort addressPersistencePort;

    public Optional<UUID> getAddressReference(String zipCode, String streetNumber) {
        return addressPersistencePort.getAddressReference(zipCode, streetNumber);
    }

    public AddressReference addAddress(Street street, StreetNumber streetNumber, City city, ZipCode zipCode) {
        final Address address = eventStore.loadAggregate(eventPublisher);
        address.handleCommand(AddAddress.of(
                street,
                streetNumber,
                city,
                zipCode
        ));
        return address.getReference();
    }

    public void editAddress(AddressReference addressReference,
                            Street street,
                            StreetNumber streetNumber,
                            City city,
                            ZipCode zipCode) throws PropertiesNotChangedException {
        Address address = eventStore.loadAggregate(addressReference, eventPublisher);
        address.handleCommand(EditAddress.of(
                street,
                streetNumber,
                city,
                zipCode
        ));
    }

    public void quitAddress(AddressReference addressReference) {
        Address address = eventStore.loadAggregate(addressReference, eventPublisher);
        address.handleCommand(QuitAddress.of());
    }

}
