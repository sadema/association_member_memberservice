package nl.kristalsoftware.association.member.datastore.types.address.eventstore;

import nl.kristalsoftware.association.member.datastore.types.address.eventstore.event.AddressBaseEventEntity;
import org.springframework.data.repository.CrudRepository;

public interface AddressEventStoreRepository extends CrudRepository<AddressBaseEventEntity, Long> {

    Iterable<AddressBaseEventEntity> findAllByZipCodeAndStreetNumber(String zipCode, String streetNumber);

}
