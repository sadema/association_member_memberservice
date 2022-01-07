package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_edited;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_signed_up.NewUUIDEventStoreRepository;
import nl.kristalsoftware.association.member.datastore.types.member.viewstore.MemberViewStore;
import nl.kristalsoftware.association.member.domain.member.Member;
import nl.kristalsoftware.association.member.domain.member.event.member_edited.MemberEdited;
import nl.kristalsoftware.association.member.domain.member.properties.MemberBirthDate;
import nl.kristalsoftware.association.member.domain.member.properties.MemberName;
import nl.kristalsoftware.datastore.base.eventstore.event.EventLoadHandler;
import nl.kristalsoftware.datastore.base.eventstore.event.EventSaveHandler;
import nl.kristalsoftware.domain.base.BaseEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberEditedHandler implements
        EventLoadHandler<Member, MemberEditedEventEntity>,
        EventSaveHandler<Member, MemberEdited> {

    private final NewUUIDEventStoreRepository eventStoreRepository;

    private final MemberViewStore memberViewStore;

    @Override
    public Class<? extends BaseEvent> appliesTo() {
        return MemberEdited.class;
    }

    @Override
    @Transactional
    public void save(final Member member, final MemberEdited memberEdited) {
        MemberEditedEventEntity memberEditedEventEntity = MemberEditedEventEntity.of(memberEdited, member.getCurrentState());
        eventStoreRepository.save(memberEditedEventEntity);
        memberViewStore.memberEdited(memberEdited);
    }

    @Override
    public void loadEvent(final Member member, final MemberEditedEventEntity eventEntity) {
        log.info("MemberEditedEventEntity: {} {} {}", eventEntity.getReference(), eventEntity.getDomainEventName(), eventEntity.getFirstName() + " " + eventEntity.getLastName());
        MemberEdited memberEdited = MemberEdited.of(
                member.getReference(),
                MemberName.of(eventEntity.getFirstName(), eventEntity.getLastName()),
                MemberBirthDate.of(eventEntity.getBirthDate())
        );
        member.loadData(memberEdited);
    }

}
