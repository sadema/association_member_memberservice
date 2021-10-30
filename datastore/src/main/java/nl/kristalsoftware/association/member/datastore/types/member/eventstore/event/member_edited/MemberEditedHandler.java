package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_edited;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.datastore.types.member.MemberPersistenceService;
import nl.kristalsoftware.association.member.domain.member.Member;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberEdited;
import nl.kristalsoftware.association.member.domain.member.properties.MemberBirthDate;
import nl.kristalsoftware.association.member.domain.member.properties.MemberName;
import nl.kristalsoftware.datastore.base.eventstore.event.EventHandler;
import nl.kristalsoftware.datastore.base.eventstore.event.message.EventMessageHandler;
import nl.kristalsoftware.domain.base.BaseEvent;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public final class MemberEditedHandler implements EventHandler<Member, MemberEditedEventEntity>, EventMessageHandler<MemberEventData> {

    private final MemberPersistenceService memberPersistenceService;

    @Override
    public Class<? extends BaseEvent> appliesTo() {
        return MemberEdited.class;
    }

    @Override
    public void save(MemberEventData memberEventData) {
        memberPersistenceService.save(MemberEditedEventEntity.of(memberEventData));
    }

    @Override
    public void loadEventData(Member member, MemberEditedEventEntity eventEntity) {
        log.info("MemberEditedEventEntity: {} {} {}", eventEntity.getReference(), eventEntity.getDomainEventName(), eventEntity.getFirstName() + " " + eventEntity.getLastName());
        MemberEdited memberEdited = MemberEdited.of(
                member.getReference(),
                MemberName.of(eventEntity.getFirstName(), eventEntity.getLastName()),
                MemberBirthDate.of(eventEntity.getBirthDate())
        );
        member.loadData(memberEdited);
    }

}
