package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_edited;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.datastore.types.member.MemberDataService;
import nl.kristalsoftware.association.member.domain.member.Member;
import nl.kristalsoftware.association.member.domain.member.MemberAddress;
import nl.kristalsoftware.association.member.domain.member.MemberBirthDate;
import nl.kristalsoftware.association.member.domain.member.MemberCity;
import nl.kristalsoftware.association.member.domain.member.MemberName;
import nl.kristalsoftware.association.member.domain.member.MemberZipCode;
import nl.kristalsoftware.association.member.domain.member.event.member_edited.MemberEdited;
import nl.kristalsoftware.datastore.base.eventstore.event.EventHandler;
import nl.kristalsoftware.datastore.base.eventstore.event.message.EventMessageHandler;
import nl.kristalsoftware.domain.base.BaseEvent;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public final class MemberEditedHandler implements EventHandler<Member, MemberEditedEventEntity>, EventMessageHandler<MemberEventData> {

    private final MemberDataService memberDataService;

    @Override
    public Class<? extends BaseEvent> appliesTo() {
        return MemberEdited.class;
    }

    @Override
    public void save(MemberEventData memberEventData) {
        memberDataService.save(MemberEditedEventEntity.of(memberEventData));
    }

    @Override
    public void loadEventData(Member member, MemberEditedEventEntity eventEntity) {
        log.info("MemberEditedEventEntity: {} {} {}", eventEntity.getReference(), eventEntity.getDomainEventName(), eventEntity.getFirstName() + " " + eventEntity.getLastName());
        MemberEdited memberEdited = MemberEdited.of(
                member.getReference(),
                MemberName.of(eventEntity.getFirstName(), eventEntity.getLastName()),
                MemberBirthDate.of(eventEntity.getBirthDate()),
                MemberAddress.of(eventEntity.getAddress()),
                MemberCity.of(eventEntity.getCity()),
                MemberZipCode.of(eventEntity.getZipCode())
        );
        member.loadData(memberEdited);
    }

}
