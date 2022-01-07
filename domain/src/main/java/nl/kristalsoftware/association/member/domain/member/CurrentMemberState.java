package nl.kristalsoftware.association.member.domain.member;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.member.properties.MemberBirthDate;
import nl.kristalsoftware.association.member.domain.member.properties.MemberKind;
import nl.kristalsoftware.association.member.domain.member.properties.MemberName;

@Data
@RequiredArgsConstructor(staticName = "of")
public class CurrentMemberState {

    private final MemberName memberName;

    private final MemberBirthDate memberBirthDate;

    private final MemberKind memberKind;

}
