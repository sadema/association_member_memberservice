package nl.kristalsoftware.association.member.domain.member;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.member.command.EditMember;
import nl.kristalsoftware.association.member.domain.member.command.SignUpMember;
import nl.kristalsoftware.association.member.domain.member.properties.MemberBirthDate;
import nl.kristalsoftware.association.member.domain.member.properties.MemberKind;
import nl.kristalsoftware.association.member.domain.member.properties.MemberName;
import nl.kristalsoftware.association.member.domain.member.properties.MemberReference;
import nl.kristalsoftware.domain.base.EventStore;
import nl.kristalsoftware.domain.base.PropertiesNotChangedException;
import nl.kristalsoftware.domain.base.annotations.DomainService;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
@DomainService
public class MemberService {

    private final EventStore<Member, MemberReference> eventStore;

    private final ApplicationEventPublisher eventPublisher;

    public MemberReference signUpMember(
            MemberName memberName,
            MemberBirthDate memberBirthDate,
            MemberKind memberKind) {
        Member member = eventStore.loadAggregate(eventPublisher);
        member.handleCommand(SignUpMember.of(memberName, memberBirthDate, memberKind));
        return member.getReference();
    }

    public void editMember(
            MemberReference memberReference,
            MemberName memberName,
            MemberBirthDate memberBirthDate,
            MemberKind memberKind) throws PropertiesNotChangedException {
        Member member = eventStore.loadAggregate(memberReference, eventPublisher);
        if (member.getMemberName().equals(memberName) &&
                member.getMemberBirthDate().equals(memberBirthDate) &&
                member.getMemberKind().equals(memberKind)) {
            throw new PropertiesNotChangedException("Memberproperties not changed!");
        }
        else {
            member.handleCommand(EditMember.of(memberName, memberBirthDate, memberKind));
        }
    }
}
