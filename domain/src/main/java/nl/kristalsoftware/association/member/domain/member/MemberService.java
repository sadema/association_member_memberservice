package nl.kristalsoftware.association.member.domain.member;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.member.command.EditMember;
import nl.kristalsoftware.association.member.domain.member.command.SignUpMember;
import nl.kristalsoftware.domain.base.PropertiesNotChangedException;
import nl.kristalsoftware.domain.base.annotations.DomainService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@DomainService
public class MemberService {

    private final MemberDataStore memberDataStore;

    private final ApplicationEventPublisher eventPublisher;

    public MemberReference signUpMember(
            MemberName memberName,
            MemberBirthDate memberBirthDate,
            MemberAddress memberAddress,
            MemberCity memberCity,
            MemberZipCode memberZipCode) {
        Member member = memberDataStore.loadAggregate(eventPublisher);
        member.handleCommand(SignUpMember.of(memberName, memberBirthDate, memberAddress, memberCity, memberZipCode));
        return member.getReference();
    }

    public void editMember(
            MemberReference memberReference,
            MemberName memberName,
            MemberBirthDate memberBirthDate,
            MemberAddress memberAddress,
            MemberCity memberCity,
            MemberZipCode memberZipCode) throws PropertiesNotChangedException {
        Member member = memberDataStore.loadAggregate(memberReference, eventPublisher);
        if (member.getMemberName().equals(memberName) &&
                member.getMemberBirthDate().equals(memberBirthDate) &&
                member.getMemberAddress().equals(memberAddress) &&
                member.getMemberCity().equals(memberCity) &&
                member.getMemberZipCode().equals(memberZipCode)) {
            throw new PropertiesNotChangedException("Member properties not changed");
        }
        member.handleCommand(EditMember.of(memberName, memberBirthDate, memberAddress, memberCity, memberZipCode));
    }
}
