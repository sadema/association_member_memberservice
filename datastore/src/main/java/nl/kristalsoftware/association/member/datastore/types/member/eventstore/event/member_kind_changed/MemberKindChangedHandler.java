package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_kind_changed;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_signed_up.NewUUIDEventStoreRepository;
import nl.kristalsoftware.association.member.datastore.types.member.viewstore.MemberViewStore;
import nl.kristalsoftware.association.member.domain.member.Member;
import nl.kristalsoftware.association.member.domain.member.event.member_kind_changed.MemberKindChanged;
import nl.kristalsoftware.association.member.domain.member.properties.MemberKind;
import nl.kristalsoftware.datastore.base.eventstore.event.EventLoadHandler;
import nl.kristalsoftware.datastore.base.eventstore.event.EventSaveHandler;
import nl.kristalsoftware.domain.base.BaseEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberKindChangedHandler implements
        EventLoadHandler<Member, MemberKindChangedEventEntity>,
        EventSaveHandler<Member, MemberKindChanged> {

    private final NewUUIDEventStoreRepository eventStoreRepository;

    private final MemberViewStore memberViewStore;

    @Override
    public Class<? extends BaseEvent> appliesTo() {
        return MemberKindChanged.class;
    }

    @Override
    @Transactional
    public void save(final Member member, final MemberKindChanged memberKindChanged) {
        eventStoreRepository.save(MemberKindChangedEventEntity.of(memberKindChanged, member.getCurrentState()));
        memberViewStore.memberKindChanged(memberKindChanged);
    }

    @Override
    public void loadEvent(final Member member, final MemberKindChangedEventEntity eventEntity) {
        log.info("MemberKindChangedEventEntity: {} {} {}", eventEntity.getReference(), eventEntity.getDomainEventName(), eventEntity.getKind());
        MemberKindChanged memberKindChanged = MemberKindChanged.of(
                member.getReference(),
                MemberKind.of(eventEntity.getKind())
        );
        member.loadData(memberKindChanged);
    }

}
