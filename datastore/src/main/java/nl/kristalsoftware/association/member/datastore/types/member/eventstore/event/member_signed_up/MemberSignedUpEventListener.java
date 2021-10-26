package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_signed_up;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.MemberEventDataBuilder;
import nl.kristalsoftware.association.member.datastore.types.member.messaging.MemberEventDataProducer;
import nl.kristalsoftware.association.member.domain.member.event.member_signed_up.MemberSignedUp;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberSignedUpEventListener {

    private final MemberEventDataBuilder memberEventDataBuilder;

    private final MemberEventDataProducer memberEventDataProducer;

    @EventListener
    public void onApplicationEvent(MemberSignedUp memberSignedUp) {
        MemberEventData memberEventData = memberEventDataBuilder.build(memberSignedUp);
        memberEventDataProducer.produce(memberEventData);
    }

}
