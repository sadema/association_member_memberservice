package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_quited;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.datastore.types.member.viewstore.MemberViewStore;
import nl.kristalsoftware.association.member.domain.member.Member;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberQuited;
import nl.kristalsoftware.datastore.base.eventstore.UUIDEventStoreRepository;
import nl.kristalsoftware.datastore.base.eventstore.event.EventLoadHandler;
import nl.kristalsoftware.datastore.base.eventstore.event.EventSaveHandler;
import nl.kristalsoftware.domain.base.BaseEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberQuitedHandler implements
        EventLoadHandler<Member, MemberQuitedEventEntity>,
        EventSaveHandler<Member, MemberQuited> {

    private final UUIDEventStoreRepository eventStoreRepository;

    private final MemberViewStore memberViewStore;

    @Override
    public Class<? extends BaseEvent> appliesTo() {
        return MemberQuited.class;
    }

    @Override
    @Transactional
    public void save(final Member member, MemberQuited memberQuited) {
        eventStoreRepository.save(MemberQuitedEventEntity.of(memberQuited));
        memberViewStore.memberQuited(memberQuited);
    }

    @Override
    public void loadEvent(final Member member, final MemberQuitedEventEntity eventEntity) {
        log.info("MemberQuitedEventEntity: {} {} {}", eventEntity.getReference(), eventEntity.getDomainEventName());
        MemberQuited memberQuited = MemberQuited.of(
                member.getReference()
        );
        member.loadData(memberQuited);
    }

}
