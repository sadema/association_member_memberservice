package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_address_unassigned;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.datastore.types.member.viewstore.MemberViewStore;
import nl.kristalsoftware.association.member.domain.address.properties.AddressReference;
import nl.kristalsoftware.association.member.domain.address.properties.StreetNumber;
import nl.kristalsoftware.association.member.domain.address.properties.ZipCode;
import nl.kristalsoftware.association.member.domain.member.Member;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberAddressUnAssigned;
import nl.kristalsoftware.datastore.base.eventstore.UUIDEventStoreRepository;
import nl.kristalsoftware.datastore.base.eventstore.event.EventHandler;
import nl.kristalsoftware.datastore.base.eventstore.event.message.EventMessageHandler;
import nl.kristalsoftware.domain.base.BaseEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberAddressUnAssignedHandler implements EventHandler<Member, MemberAddressUnAssignedEventEntity>, EventMessageHandler<MemberEventData> {

    private final UUIDEventStoreRepository eventStoreRepository;

    private final MemberViewStore memberViewStore;

    @Override
    public Class<? extends BaseEvent> appliesTo() {
        return MemberAddressUnAssigned.class;
    }

    @Override
    @Transactional
    public void save(final MemberEventData memberEventData) {
        eventStoreRepository.save(MemberAddressUnAssignedEventEntity.of(memberEventData));
        memberViewStore.memberAddressUnAssigned(memberEventData);
    }

    @Override
    public void loadEventData(final Member member, final MemberAddressUnAssignedEventEntity eventEntity) {
        log.info("MemberAddressUnAssignedEventEntity: {} {} {}", eventEntity.getZipCode(), eventEntity.getStreetNumber(), eventEntity.getDomainEventName());
        MemberAddressUnAssigned memberAddressUnAssigned = MemberAddressUnAssigned.of(
                member.getReference(),
                AddressReference.of(ZipCode.of(eventEntity.getZipCode()), StreetNumber.of(eventEntity.getStreetNumber()))
        );
        member.loadData(memberAddressUnAssigned);
    }

}
