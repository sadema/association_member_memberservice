package nl.kristalsoftware.association.member.domain.address;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.domain.address.command.AddAddress;
import nl.kristalsoftware.association.member.domain.address.command.EditAddress;
import nl.kristalsoftware.association.member.domain.address.properties.AddressReference;
import nl.kristalsoftware.association.member.domain.address.properties.CompoundAddress;
import nl.kristalsoftware.association.member.domain.member.properties.MemberReference;
import nl.kristalsoftware.domain.base.EventStore;
import nl.kristalsoftware.domain.base.PersistenceHandlerPort;
import nl.kristalsoftware.domain.base.annotations.DomainService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@DomainService
@Service
public class AddressService {

    private final EventStore<Address, AddressReference> eventStore;

    private final PersistenceHandlerPort<Address> persistenceHandler;


//    public AddressReference addAddress(CompoundAddress compoundAddress) {
//        final Address address = eventStore.loadAggregate(compoundAddress.getAddressReference(), eventPublisher);
//        if (address.notExists()) {
//            address.handleCommand(AddAddress.of(compoundAddress));
//        }
//        return address.getReference();
//    }

//    public void editAddress(CompoundAddress compoundAddress) throws PropertiesNotChangedException {
//        Address address = eventStore.loadAggregate(compoundAddress.getAddressReference(), eventPublisher);
//        address.handleCommand(EditAddress.of(compoundAddress));
//    }

//    public void quitAddress(AddressReference addressReference) {
//        Address address = eventStore.loadAggregate(addressReference, eventPublisher);
//        address.handleCommand(QuitAddress.of());
//    }

    public void processAddresses(List<CompoundAddress> memberAddresses, MemberReference memberReference) {
        for (CompoundAddress compoundAddress : memberAddresses) {
            Address address = eventStore.loadAggregate(AddressReference.of(compoundAddress.getZipCode(), compoundAddress.getStreetNumber()), persistenceHandler);
            if (address.notExists()) {
                address.handleCommand(AddAddress.of(compoundAddress));
            }
            else {
                address.handleCommand(EditAddress.of(compoundAddress));
            }
        }
    }
}
