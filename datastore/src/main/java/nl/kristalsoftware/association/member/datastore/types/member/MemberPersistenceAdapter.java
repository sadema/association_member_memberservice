package nl.kristalsoftware.association.member.datastore.types.member;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.member.Member;
import nl.kristalsoftware.datastore.base.eventstore.event.EventSaveHandler;
import nl.kristalsoftware.datastore.base.eventstore.event.entity.EventHandlerProvider;
import nl.kristalsoftware.domain.base.BaseEvent;
import nl.kristalsoftware.domain.base.PersistenceHandlerPort;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class MemberPersistenceAdapter implements PersistenceHandlerPort<Member> {

    private final EventHandlerProvider eventHandlerProvider;

    public <T extends BaseEvent> void save(Member member, T domainEvent) {
        EventSaveHandler eventHandler = eventHandlerProvider.getEventSaveHandler(domainEvent.getClass().getSimpleName());
        eventHandler.save(member, domainEvent);
    }

}
