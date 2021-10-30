package nl.kristalsoftware.association.member.domain.member.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.kristalsoftware.association.member.domain.member.properties.MemberBirthDate;
import nl.kristalsoftware.association.member.domain.member.properties.MemberKind;
import nl.kristalsoftware.association.member.domain.member.properties.MemberName;

@Data
@AllArgsConstructor(staticName = "of")
public class EditMember {

    private MemberName memberName;

    private MemberBirthDate memberBirthDate;

    private MemberKind memberKind;

}
