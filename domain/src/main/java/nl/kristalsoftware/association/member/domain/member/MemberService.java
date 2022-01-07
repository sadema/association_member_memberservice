package nl.kristalsoftware.association.member.domain.member;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.domain.address.properties.CompoundAddress;
import nl.kristalsoftware.association.member.domain.member.command.EditMember;
import nl.kristalsoftware.association.member.domain.member.command.ProcessMemberAddresses;
import nl.kristalsoftware.association.member.domain.member.command.QuitMember;
import nl.kristalsoftware.association.member.domain.member.command.SignUpMember;
import nl.kristalsoftware.association.member.domain.member.properties.MemberBirthDate;
import nl.kristalsoftware.association.member.domain.member.properties.MemberKind;
import nl.kristalsoftware.association.member.domain.member.properties.MemberName;
import nl.kristalsoftware.association.member.domain.member.properties.MemberReference;
import nl.kristalsoftware.domain.base.EventStore;
import nl.kristalsoftware.domain.base.PersistenceHandlerPort;
import nl.kristalsoftware.domain.base.annotations.DomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
@DomainService
public class MemberService {

    private final EventStore<Member, MemberReference> eventStore;

    private final PersistenceHandlerPort<Member> persistenceHandler;

    public MemberReference signUpMember(
            MemberName memberName,
            MemberBirthDate memberBirthDate,
            MemberKind memberKind) {
        Member member = eventStore.loadAggregate(MemberReference.of(UUID.randomUUID()), persistenceHandler);
        member.handleCommand(SignUpMember.of(memberName, memberBirthDate, memberKind));
        return member.getReference();
    }

    public void editMember(
            MemberReference memberReference,
            MemberName memberName,
            MemberBirthDate memberBirthDate,
            MemberKind memberKind,
            List<CompoundAddress> inMemberAddresses) {
        Member member = eventStore.loadAggregate(memberReference, persistenceHandler);
        if (member.exists()) {
            member.handleCommand(EditMember.of(memberName, memberBirthDate, memberKind));
            member.handleCommand(ProcessMemberAddresses.of(inMemberAddresses));
        }
    }

    public void quitMember(MemberReference memberReference) {
        Member member = eventStore.loadAggregate(memberReference, persistenceHandler);
        if (member.exists()) {
            member.handleCommand(QuitMember.of());
        }
    }

}
