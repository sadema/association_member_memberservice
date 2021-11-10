package nl.kristalsoftware.association.member.domain.address;

import java.util.Optional;
import java.util.UUID;

public interface AddressPersistencePort {

    Optional<UUID> getAddressReference(String zipCode, String streetNumber);
    
}
