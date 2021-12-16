package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event;

import nl.kristalsoftware.association.member.domain.member.Member;
import nl.kristalsoftware.datastore.base.eventstore.event.UUIDEventFinder;
import org.springframework.stereotype.Component;

@Component
public class MemberEventFinder extends UUIDEventFinder<Member> {
}
