package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_address_assigned;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.datastore.types.member.viewstore.MemberViewStore;
import nl.kristalsoftware.association.member.domain.address.properties.StreetNumber;
import nl.kristalsoftware.association.member.domain.address.properties.ZipCode;
import nl.kristalsoftware.association.member.domain.member.Member;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberAddressAssigned;
import nl.kristalsoftware.datastore.base.eventstore.UUIDEventStoreRepository;
import nl.kristalsoftware.datastore.base.eventstore.event.EventHandler;
import nl.kristalsoftware.datastore.base.eventstore.event.message.EventMessageHandler;
import nl.kristalsoftware.domain.base.BaseEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberAddressAssignedHandler implements EventHandler<Member, MemberAddressAssignedEventEntity>, EventMessageHandler<MemberEventData> {

    private final UUIDEventStoreRepository eventStoreRepository;

    private final MemberViewStore memberViewStore;

    @Override
    public Class<? extends BaseEvent> appliesTo() {
        return MemberAddressAssigned.class;
    }

    @Override
    @Transactional
    public void save(final MemberEventData memberEventData) {
        eventStoreRepository.save(MemberAddressAssignedEventEntity.of(memberEventData));
        memberViewStore.memberAddressAssigned(memberEventData);
    }

    @Override
    public void loadEventData(final Member member, final MemberAddressAssignedEventEntity eventEntity) {
        log.info("MemberAddressAssignedEventEntity: {} {} {}", eventEntity.getZipCode(), eventEntity.getStreetNumber(), eventEntity.getDomainEventName());
        MemberAddressAssigned memberAddressAssigned = MemberAddressAssigned.of(
                member.getReference(),
                ZipCode.of(eventEntity.getZipCode()),
                StreetNumber.of(eventEntity.getStreetNumber())
        );
        member.loadData(memberAddressAssigned);
    }

}
