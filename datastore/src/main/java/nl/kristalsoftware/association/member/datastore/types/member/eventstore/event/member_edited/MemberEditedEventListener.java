package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_edited;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.MemberEventDataBuilder;
import nl.kristalsoftware.association.member.datastore.types.member.messaging.MemberEventDataProducer;
import nl.kristalsoftware.association.member.domain.member.event.member_edited.MemberEdited;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class MemberEditedEventListener {

    private final MemberEventDataBuilder memberEventDataBuilder;

    private final MemberEventDataProducer memberEventDataProducer;

    @EventListener
    public void onApplicationEvent(MemberEdited memberEdited) {
        MemberEventData memberEventData = memberEventDataBuilder.build(memberEdited);
        memberEventDataProducer.produce(memberEventData);
    }

}
