package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_quited;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.datastore.types.member.MemberPersistenceService;
import nl.kristalsoftware.association.member.domain.member.Member;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberQuited;
import nl.kristalsoftware.datastore.base.eventstore.event.EventHandler;
import nl.kristalsoftware.datastore.base.eventstore.event.message.EventMessageHandler;
import nl.kristalsoftware.domain.base.BaseEvent;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public final class MemberQuitedHandler implements EventHandler<Member, MemberQuitedEventEntity>, EventMessageHandler<MemberEventData> {

    private final MemberPersistenceService memberPersistenceService;

    @Override
    public Class<? extends BaseEvent> appliesTo() {
        return MemberQuited.class;
    }

    @Override
    public void save(MemberEventData memberEventData) {
        memberPersistenceService.save(MemberQuitedEventEntity.of(memberEventData));
    }

    @Override
    public void loadEventData(Member member, MemberQuitedEventEntity eventEntity) {
        log.info("MemberQuitedEventEntity: {} {} {}", eventEntity.getReference(), eventEntity.getDomainEventName());
        MemberQuited memberQuited = MemberQuited.of(
                member.getReference()
        );
        member.loadData(memberQuited);
    }

}
