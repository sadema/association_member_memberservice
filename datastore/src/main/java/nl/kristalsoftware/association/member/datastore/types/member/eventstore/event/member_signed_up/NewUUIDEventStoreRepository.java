package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_signed_up;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface NewUUIDEventStoreRepository extends CrudRepository<AbstractUUIDBaseEventEntity, Long> {

    Iterable<AbstractUUIDBaseEventEntity> findAllByReference(UUID value);

}
