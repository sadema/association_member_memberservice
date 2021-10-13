package nl.kristalsoftware.association.member.domain.member.event.member_signed_up;

import lombok.Getter;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.domain.base.BaseEvent;

public class MemberSignedUp implements BaseEvent<MemberEventData> {

    @Getter
    private final MemberEventData eventData;

    public MemberSignedUp(MemberEventData eventData) {
        this.eventData = eventData;
        this.eventData.setDomainEventName(this.getClass().getSimpleName());
    }

}
