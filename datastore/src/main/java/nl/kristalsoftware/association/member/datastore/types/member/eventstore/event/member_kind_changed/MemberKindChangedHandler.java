package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_kind_changed;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.datastore.types.member.viewstore.MemberViewStore;
import nl.kristalsoftware.association.member.domain.member.Member;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberKindChanged;
import nl.kristalsoftware.association.member.domain.member.properties.MemberKind;
import nl.kristalsoftware.datastore.base.eventstore.UUIDEventStoreRepository;
import nl.kristalsoftware.datastore.base.eventstore.event.EventHandler;
import nl.kristalsoftware.datastore.base.eventstore.event.message.EventMessageHandler;
import nl.kristalsoftware.domain.base.BaseEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberKindChangedHandler implements EventHandler<Member, MemberKindChangedEventEntity>, EventMessageHandler<MemberEventData> {

    private final UUIDEventStoreRepository eventStoreRepository;

    private final MemberViewStore memberViewStore;

    @Override
    public Class<? extends BaseEvent> appliesTo() {
        return MemberKindChanged.class;
    }

    @Override
    @Transactional
    public void save(final MemberEventData memberEventData) {
        eventStoreRepository.save(MemberKindChangedEventEntity.of(memberEventData));
        memberViewStore.memberKindChanged(memberEventData);
    }

    @Override
    public void loadEventData(final Member member, final MemberKindChangedEventEntity eventEntity) {
        log.info("MemberKindChangedEventEntity: {} {} {}", eventEntity.getReference(), eventEntity.getDomainEventName(), eventEntity.getKind());
        MemberKindChanged memberKindChanged = MemberKindChanged.of(
                member.getReference(),
                MemberKind.of(eventEntity.getKind())
        );
        member.loadData(memberKindChanged);
    }

}
