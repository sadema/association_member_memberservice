package nl.kristalsoftware.association.member.domain.member;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.member.command.ChangeMemberKind;
import nl.kristalsoftware.association.member.domain.member.command.EditMember;
import nl.kristalsoftware.association.member.domain.member.command.QuitMember;
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
        Boolean propertiesChanged = hasPropertiesChanged(member, member.getMemberName(), member.getMemberBirthDate());
        Boolean memberKindChanged = hasMemberKindChanged(member, memberKind);
        if (propertiesChanged || memberKindChanged) {
            if (propertiesChanged) {
                member.handleCommand(EditMember.of(memberName, memberBirthDate, memberKind));
            }
            if (memberKindChanged) {
                member.handleCommand(ChangeMemberKind.of(memberKind));
            }
        }
        else {
            throw new PropertiesNotChangedException("Memberproperties not changed!");
        }
    }

    private Boolean hasPropertiesChanged(Member member, MemberName memberName, MemberBirthDate memberBirthDate) {
        return !(member.getMemberName().equals(memberName) &&
                member.getMemberBirthDate().equals(memberBirthDate));
    }

    private Boolean hasMemberKindChanged(Member member, MemberKind memberKind) {
        return !member.getMemberKind().equals(memberKind);
    }

    public void quitMember(MemberReference memberReference) {
        Member member = eventStore.loadAggregate(memberReference, eventPublisher);
        member.handleCommand(QuitMember.of());
    }
}
