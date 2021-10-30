package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.datastore.types.member.messaging.MemberEventDataProducer;
import nl.kristalsoftware.association.member.domain.member.event.MemberEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberEventListener {

    private final MemberEventDataProducer memberEventDataProducer;

    @EventListener
    public void onApplicationEvent(MemberEvent event) {
        MemberEventData memberEventData = MemberEventData.newBuilder()
                .setDomainEventName(event.getDomainEventName().name())
                .setReference(event.getMemberReference().getStringValue())
                .setFirstName(event.getMemberName().getFirstName())
                .setLastName(event.getMemberName().getLastName())
                .setBirthDate(event.getMemberBirthDate().getDateInMillis())
                .setKind(event.getMemberKind().getValue().name())
                .build();
        memberEventDataProducer.produce(memberEventData);
    }

}
