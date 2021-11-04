package nl.kristalsoftware.association.member.domain.member.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.kristalsoftware.association.member.domain.member.properties.MemberKind;

@Data
@AllArgsConstructor(staticName = "of")
public class ChangeMemberKind {

    private MemberKind memberKind;

}
