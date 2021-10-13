package nl.kristalsoftware.association.member.domain.member.event.member_signed_up;

import lombok.Builder;
import lombok.Getter;
import nl.kristalsoftware.association.member.domain.member.MemberAddress;
import nl.kristalsoftware.association.member.domain.member.MemberBirthDate;
import nl.kristalsoftware.association.member.domain.member.MemberCity;
import nl.kristalsoftware.association.member.domain.member.MemberName;
import nl.kristalsoftware.association.member.domain.member.MemberZipCode;

@Builder
@Getter
public class MemberSignedUpEventData {

    private MemberName memberName;

    private MemberBirthDate memberBirthDate;

    private MemberAddress memberAddress;

    private MemberCity memberCity;

    private MemberZipCode memberZipCode;

}
