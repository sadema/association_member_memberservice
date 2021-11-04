package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_kind_changed;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.datastore.types.member.MemberPersistenceService;
import nl.kristalsoftware.association.member.domain.member.Member;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberKindChanged;
import nl.kristalsoftware.association.member.domain.member.properties.MemberKind;
import nl.kristalsoftware.datastore.base.eventstore.event.EventHandler;
import nl.kristalsoftware.datastore.base.eventstore.event.message.EventMessageHandler;
import nl.kristalsoftware.domain.base.BaseEvent;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public final class MemberKindChangedHandler implements EventHandler<Member, MemberKindChangedEventEntity>, EventMessageHandler<MemberEventData> {

    private final MemberPersistenceService memberPersistenceService;

    @Override
    public Class<? extends BaseEvent> appliesTo() {
        return MemberKindChanged.class;
    }

    @Override
    public void save(MemberEventData memberEventData) {
        memberPersistenceService.save(MemberKindChangedEventEntity.of(memberEventData));
    }

    @Override
    public void loadEventData(Member member, MemberKindChangedEventEntity eventEntity) {
        log.info("MemberKindChangedEventEntity: {} {} {}", eventEntity.getReference(), eventEntity.getDomainEventName(), eventEntity.getKind());
        MemberKindChanged memberKindChanged = MemberKindChanged.of(
                member.getReference(),
                MemberKind.of(eventEntity.getKind())
        );
        member.loadData(memberKindChanged);
    }

}
