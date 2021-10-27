package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_signed_up;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.datastore.types.member.messaging.MemberEventDataProducer;
import nl.kristalsoftware.association.member.domain.member.event.member_signed_up.MemberSignedUp;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberSignedUpEventListener {

    private final MemberEventDataProducer memberEventDataProducer;

    @EventListener
    public void onApplicationEvent(MemberSignedUp event) {
        MemberEventData memberEventData = MemberEventData.newBuilder()
                .setDomainEventName(event.getClass().getSimpleName())
                .setReference(event.getMemberReference().getStringValue())
                .setFirstName(event.getMemberName().getFirstName())
                .setLastName(event.getMemberName().getLastName())
                .setBirthDate(event.getMemberBirthDate().getDateInMillis())
                .setAddress(event.getMemberAddress().getValue())
                .setCity(event.getMemberCity().getValue())
                .setZip(event.getMemberZipCode().getValue())
                .build();
        memberEventDataProducer.produce(memberEventData);
    }

}
