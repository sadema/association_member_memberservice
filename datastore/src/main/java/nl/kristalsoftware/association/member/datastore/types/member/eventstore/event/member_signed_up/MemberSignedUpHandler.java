package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_signed_up;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.datastore.types.member.MemberPersistenceService;
import nl.kristalsoftware.association.member.domain.member.Member;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberSignedUp;
import nl.kristalsoftware.association.member.domain.member.properties.MemberBirthDate;
import nl.kristalsoftware.association.member.domain.member.properties.MemberKind;
import nl.kristalsoftware.association.member.domain.member.properties.MemberName;
import nl.kristalsoftware.datastore.base.eventstore.event.EventHandler;
import nl.kristalsoftware.datastore.base.eventstore.event.message.EventMessageHandler;
import nl.kristalsoftware.domain.base.BaseEvent;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public final class MemberSignedUpHandler implements EventHandler<Member, MemberSignedUpEventEntity>, EventMessageHandler<MemberEventData> {

    private final MemberPersistenceService memberPersistenceService;

    @Override
    public Class<? extends BaseEvent> appliesTo() {
        return MemberSignedUp.class;
    }

    @Override
    public void save(MemberEventData memberEventData) {
        memberPersistenceService.save(MemberSignedUpEventEntity.of(memberEventData));
    }

    @Override
    public void loadEventData(Member member, MemberSignedUpEventEntity eventEntity) {
        log.info("MemberSignedUpEventEntity: {} {} {}", eventEntity.getReference(), eventEntity.getDomainEventName(), eventEntity.getFirstName() + " " + eventEntity.getLastName());
        MemberSignedUp memberSignedUp = MemberSignedUp.of(
                member.getReference(),
                MemberName.of(eventEntity.getFirstName(), eventEntity.getLastName()),
                MemberBirthDate.of(eventEntity.getBirthDate()),
                MemberKind.of(eventEntity.getKind())
        );
        member.loadData(memberSignedUp);
    }

}
