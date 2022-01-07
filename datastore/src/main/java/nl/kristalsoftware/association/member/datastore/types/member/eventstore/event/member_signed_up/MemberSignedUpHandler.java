package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_signed_up;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.datastore.types.member.viewstore.MemberViewStore;
import nl.kristalsoftware.association.member.domain.member.Member;
import nl.kristalsoftware.association.member.domain.member.event.MemberEventDefinition;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberSignedUp;
import nl.kristalsoftware.association.member.domain.member.properties.MemberBirthDate;
import nl.kristalsoftware.association.member.domain.member.properties.MemberKind;
import nl.kristalsoftware.association.member.domain.member.properties.MemberName;
import nl.kristalsoftware.datastore.base.eventstore.event.EventLoadHandler;
import nl.kristalsoftware.datastore.base.eventstore.event.EventSaveHandler;
import nl.kristalsoftware.domain.base.BaseEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberSignedUpHandler implements
        EventLoadHandler<Member, MemberSignedUpEventEntity>,
        EventSaveHandler<Member, MemberSignedUp> {


    private final NewUUIDEventStoreRepository eventStoreRepository;

    private final MemberViewStore memberViewStore;

    @Override
    public Class<? extends BaseEvent> appliesTo() {
        return MemberSignedUp.class;
    }

    @Transactional
    public void save(final Member member, final MemberSignedUp memberSignedUp) {
        eventStoreRepository.save(MemberSignedUpEventEntity.of(memberSignedUp));
        memberViewStore.memberSignedUp(memberSignedUp);
    }


    @Override
    public void loadEvent(final Member member, final MemberSignedUpEventEntity eventEntity) {
        log.info("MemberSignedUpEventEntity: {} {} {}", eventEntity.getReference(), eventEntity.getDomainEventName(), eventEntity.getFirstName() + " " + eventEntity.getLastName());
        MemberSignedUp memberSignedUp = MemberSignedUp.of(
                MemberEventDefinition.valueOf(eventEntity.getDomainEventName()),
                member.getReference(),
                MemberName.of(eventEntity.getFirstName(), eventEntity.getLastName()),
                MemberBirthDate.of(eventEntity.getBirthDate()),
                MemberKind.of(eventEntity.getKind())
        );
        member.loadData(memberSignedUp);
    }

}
