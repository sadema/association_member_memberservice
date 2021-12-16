package nl.kristalsoftware.association.member.domain.concerned_person.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.kristalsoftware.association.member.domain.member.event.MemberEventDefinition;
import nl.kristalsoftware.association.member.domain.member.properties.MemberBirthDate;
import nl.kristalsoftware.association.member.domain.member.properties.MemberKind;
import nl.kristalsoftware.association.member.domain.member.properties.MemberName;
import nl.kristalsoftware.association.member.domain.member.properties.MemberReference;
import nl.kristalsoftware.domain.base.BaseEvent;

@Data
@AllArgsConstructor(staticName = "of")
public class ConcernedPersonEvent implements BaseEvent {

    private MemberReference memberReference;

    private MemberEventDefinition domainEventName;

    private MemberName memberName;

    private MemberBirthDate memberBirthDate;

    private MemberKind memberKind;

}
