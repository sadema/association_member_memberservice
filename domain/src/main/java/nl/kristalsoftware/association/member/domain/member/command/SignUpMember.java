package nl.kristalsoftware.association.member.domain.member.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.kristalsoftware.association.member.domain.member.MemberAddress;
import nl.kristalsoftware.association.member.domain.member.MemberBirthDate;
import nl.kristalsoftware.association.member.domain.member.MemberCity;
import nl.kristalsoftware.association.member.domain.member.MemberName;
import nl.kristalsoftware.association.member.domain.member.MemberZipCode;

@Data
@AllArgsConstructor(staticName = "of")
public class SignUpMember {

    private MemberName memberName;

    private MemberBirthDate memberBirthDate;

    private MemberAddress memberAddress;

    private MemberCity memberCity;

    private MemberZipCode memberZipCode;

}
