package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_signed_up;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.datastore.types.member.MemberPersistenceService;
import nl.kristalsoftware.association.member.domain.member.Member;
import nl.kristalsoftware.association.member.domain.member.MemberAddress;
import nl.kristalsoftware.association.member.domain.member.MemberBirthDate;
import nl.kristalsoftware.association.member.domain.member.MemberCity;
import nl.kristalsoftware.association.member.domain.member.MemberName;
import nl.kristalsoftware.association.member.domain.member.MemberZipCode;
import nl.kristalsoftware.association.member.domain.member.event.member_signed_up.MemberSignedUp;
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
                MemberAddress.of(eventEntity.getAddress()),
                MemberCity.of(eventEntity.getCity()),
                MemberZipCode.of(eventEntity.getZipCode())
        );
        member.loadData(memberSignedUp);
    }

}
