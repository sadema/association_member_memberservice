package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.AddressDetails;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.datastore.types.member.messaging.MemberEventDataProducer;
import nl.kristalsoftware.association.member.domain.member.event.MemberEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberEventListener {

    private final MemberEventDataProducer memberEventDataProducer;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void onApplicationEvent(MemberEventData memberEventData) {
        memberEventDataProducer.produce(memberEventData);
    }

    @EventListener
    public void onApplicationEvent(MemberEvent event) {
        AddressDetails addressDetails = null;
        if (event.getAddress() != null) {
            addressDetails = AddressDetails.newBuilder()
                    .setZipCode(event.getAddress().getZipCode().getValue())
                    .setStreetNumber(event.getAddress().getStreetNumber().getValue())
                    .setStreet(event.getAddress().getStreet().getValue())
                    .setCity(event.getAddress().getCity().getValue())
                    .build();
        }
        MemberEventData memberEventData = MemberEventData.newBuilder()
                .setDomainEventName(event.getDomainEventName().name())
                .setReference(event.getMemberReference().getValue())
                .setFirstName(event.getMemberName().getFirstName())
                .setLastName(event.getMemberName().getLastName())
                .setBirthDate(event.getMemberBirthDate().getInstant())
                .setKind(event.getMemberKind().getValue().name())
                .setAddress(addressDetails)
                .build();
        memberEventDataProducer.produce(memberEventData);
    }

}
