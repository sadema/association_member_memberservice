package nl.kristalsoftware.association.member.datastore.types.member.eventstore;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.datastore.base.eventstore.event.EventHandler;
import nl.kristalsoftware.datastore.base.eventstore.event.entity.BaseEventEntity;
import nl.kristalsoftware.datastore.base.eventstore.event.entity.EventEntityHandlerProvider;
import nl.kristalsoftware.domain.base.Aggregate;
import org.springframework.context.ApplicationEventPublisher;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class NewBaseEventStore<T extends Aggregate> {

    private final EventEntityHandlerProvider eventEntityHandlerProvider;

    private List<BaseEventEntity> getEvents(T aggregateRoot) {
         return StreamSupport.stream(
                findAllByReference(aggregateRoot).spliterator(), false)
                    .collect(Collectors.toList());
    }

    private void loadEvents(T aggregateRoot) {
        List<BaseEventEntity> eventEntities = getEvents(aggregateRoot);
        for (BaseEventEntity eventEntity : eventEntities) {
            EventHandler eventHandler = eventEntityHandlerProvider.getEventEntityHandler(eventEntity.getDomainEventName());
            eventHandler.loadEventData(aggregateRoot, eventEntity);
        }
    }

    public T loadEvents(UUID reference, ApplicationEventPublisher applicationEventPublisher) {
        T aggregateRoot = createAggregateRoot(reference, applicationEventPublisher);
        loadEvents(aggregateRoot);
        return aggregateRoot;
    }

    protected abstract Iterable<BaseEventEntity> findAllByReference(T aggregateRoot);

    protected abstract T createAggregateRoot(UUID reference, ApplicationEventPublisher applicationEventPublisher);

//    public abstract <V extends BaseEventEntity> void saveEventEntity(V eventEntity);

}
