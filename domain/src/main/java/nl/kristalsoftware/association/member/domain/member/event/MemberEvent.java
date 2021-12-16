package nl.kristalsoftware.association.member.domain.member.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.kristalsoftware.association.member.domain.address.properties.CompoundAddress;
import nl.kristalsoftware.association.member.domain.member.properties.MemberBirthDate;
import nl.kristalsoftware.association.member.domain.member.properties.MemberKind;
import nl.kristalsoftware.association.member.domain.member.properties.MemberName;
import nl.kristalsoftware.association.member.domain.member.properties.MemberReference;
import nl.kristalsoftware.domain.base.BaseEvent;

@Data
@AllArgsConstructor(staticName = "of")
public class MemberEvent implements BaseEvent {

    private MemberReference memberReference;

    private MemberEventDefinition domainEventName;

    private MemberName memberName;

    private MemberBirthDate memberBirthDate;

    private MemberKind memberKind;

    private CompoundAddress address;

//    public static MemberEvent of(
//            MemberReference memberReference,
//            MemberEventDefinition domainEventName,
//            MemberName memberName,
//            MemberBirthDate memberBirthDate,
//            MemberKind memberKind,
//            CompoundAddress compoundAddress
//    ) {
//        return new MemberEvent(
//                memberReference,
//                domainEventName,
//                memberName,
//                memberBirthDate,
//                memberKind,
//                compoundAddress
//        );
//    }

}
