package nl.kristalsoftware.association.member.domain.member.event.member_edited;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.member.MemberAddress;
import nl.kristalsoftware.association.member.domain.member.MemberBirthDate;
import nl.kristalsoftware.association.member.domain.member.MemberCity;
import nl.kristalsoftware.association.member.domain.member.MemberName;
import nl.kristalsoftware.association.member.domain.member.MemberReference;
import nl.kristalsoftware.association.member.domain.member.MemberZipCode;
import nl.kristalsoftware.domain.base.BaseEvent;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class MemberEdited implements BaseEvent {

    private final MemberReference memberReference;

    private final MemberName memberName;

    private final MemberBirthDate memberBirthDate;

    private final MemberAddress memberAddress;

    private final MemberCity memberCity;

    private final MemberZipCode memberZipCode;

}
