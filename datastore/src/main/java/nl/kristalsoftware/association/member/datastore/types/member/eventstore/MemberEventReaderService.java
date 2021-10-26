package nl.kristalsoftware.association.member.datastore.types.member.eventstore;

import nl.kristalsoftware.association.member.domain.member.Member;
import nl.kristalsoftware.association.member.domain.member.MemberReference;
import nl.kristalsoftware.datastore.base.eventstore.EventStoreRepository;
import nl.kristalsoftware.datastore.base.eventstore.event.entity.BaseEventEntity;
import nl.kristalsoftware.datastore.base.eventstore.event.entity.EventEntityHandlerProvider;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MemberEventReaderService extends NewBaseEventStore<Member> {

    private final EventStoreRepository eventStoreRepository;

    public MemberEventReaderService(EventEntityHandlerProvider eventEntityHandlerProvider, EventStoreRepository eventStoreRepository) {
        super(eventEntityHandlerProvider);
        this.eventStoreRepository = eventStoreRepository;
    }

    @Override
    protected Iterable<BaseEventEntity> findAllByReference(Member aggregateRoot) {
        return eventStoreRepository.findAllByReference(aggregateRoot.getReference().getValue());
    }

    @Override
    protected Member createAggregateRoot(UUID reference, ApplicationEventPublisher applicationEventPublisher) {
        return Member.of(MemberReference.of(reference), applicationEventPublisher);
    }

}
