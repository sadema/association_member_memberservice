package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_quited;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.datastore.types.member.viewstore.MemberViewStore;
import nl.kristalsoftware.association.member.domain.member.Member;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberQuited;
import nl.kristalsoftware.datastore.base.eventstore.UUIDEventStoreRepository;
import nl.kristalsoftware.datastore.base.eventstore.event.EventHandler;
import nl.kristalsoftware.datastore.base.eventstore.event.message.EventMessageHandler;
import nl.kristalsoftware.domain.base.BaseEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberQuitedHandler implements EventHandler<Member, MemberQuitedEventEntity>, EventMessageHandler<MemberEventData> {

    private final UUIDEventStoreRepository eventStoreRepository;

    private final MemberViewStore memberViewStore;

    @Override
    public Class<? extends BaseEvent> appliesTo() {
        return MemberQuited.class;
    }

    @Override
    @Transactional
    public void save(final MemberEventData memberEventData) {
        eventStoreRepository.save(MemberQuitedEventEntity.of(memberEventData));
        memberViewStore.memberQuited(memberEventData);
    }

    @Override
    public void loadEventData(final Member member, final MemberQuitedEventEntity eventEntity) {
        log.info("MemberQuitedEventEntity: {} {} {}", eventEntity.getReference(), eventEntity.getDomainEventName());
        MemberQuited memberQuited = MemberQuited.of(
                member.getReference()
        );
        member.loadData(memberQuited);
    }

}
