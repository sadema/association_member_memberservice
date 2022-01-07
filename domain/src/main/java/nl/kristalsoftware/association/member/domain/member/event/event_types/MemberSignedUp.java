package nl.kristalsoftware.association.member.domain.member.event.event_types;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.member.event.MemberEventDefinition;
import nl.kristalsoftware.association.member.domain.member.properties.MemberBirthDate;
import nl.kristalsoftware.association.member.domain.member.properties.MemberKind;
import nl.kristalsoftware.association.member.domain.member.properties.MemberName;
import nl.kristalsoftware.association.member.domain.member.properties.MemberReference;
import nl.kristalsoftware.domain.base.BaseEvent;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class MemberSignedUp implements BaseEvent {

    private final MemberEventDefinition domainEventName;

    private final MemberReference memberReference;

    private final MemberName memberName;

    private final MemberBirthDate memberBirthDate;

    private final MemberKind memberKind;

}
