package nl.kristalsoftware.association.member.domain.member.event.member_edited;

import lombok.Getter;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.domain.base.BaseEvent;

public class MemberEdited implements BaseEvent<MemberEventData> {

    @Getter
    private final MemberEventData eventData;

    public MemberEdited(MemberEventData eventData) {
        this.eventData = eventData;
        this.eventData.setDomainEventName(this.getClass().getSimpleName());
    }

}
