package nl.kristalsoftware.association.member.domain.member;

import lombok.Getter;
import nl.kristalsoftware.association.member.domain.member.command.ChangeMemberKind;
import nl.kristalsoftware.association.member.domain.member.command.EditMember;
import nl.kristalsoftware.association.member.domain.member.command.QuitMember;
import nl.kristalsoftware.association.member.domain.member.command.SignUpMember;
import nl.kristalsoftware.association.member.domain.member.event.MemberEvent;
import nl.kristalsoftware.association.member.domain.member.event.MemberEventDefinition;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberEdited;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberKindChanged;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberQuited;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberSignedUp;
import nl.kristalsoftware.association.member.domain.member.properties.MemberBirthDate;
import nl.kristalsoftware.association.member.domain.member.properties.MemberKind;
import nl.kristalsoftware.association.member.domain.member.properties.MemberName;
import nl.kristalsoftware.association.member.domain.member.properties.MemberReference;
import nl.kristalsoftware.domain.base.Aggregate;
import nl.kristalsoftware.domain.base.BaseAggregateRoot;
import nl.kristalsoftware.domain.base.annotations.AggregateRoot;
import org.springframework.context.ApplicationEventPublisher;

@Getter
@AggregateRoot
public class Member extends BaseAggregateRoot<MemberReference> implements Aggregate {

    private MemberName memberName;

    private MemberBirthDate memberBirthDate;

    private MemberKind memberKind;

    private Member(MemberReference reference, ApplicationEventPublisher eventPublisher) {
        super(reference, eventPublisher);
    }

    public static Member of(MemberReference reference, ApplicationEventPublisher eventPublisher) {
        return new Member(reference, eventPublisher);
    }

    public void loadData(MemberSignedUp memberSignedUp) {
        memberName = memberSignedUp.getMemberName();
        memberBirthDate = memberSignedUp.getMemberBirthDate();
        memberKind = memberSignedUp.getMemberKind();
    }

    public void loadData(MemberEdited memberEdited) {
        memberName = memberEdited.getMemberName();
        memberBirthDate = memberEdited.getMemberBirthDate();
    }

    public void loadData(MemberKindChanged memberKindChanged) {
        memberKind = memberKindChanged.getMemberKind();
    }

    public void loadData(MemberQuited memberQuited) {
        // TODO: deleted flag zetten???
    }

    public void handleCommand(SignUpMember command) {
        sendEvent(MemberEvent.of(getReference(),
                MemberEventDefinition.MemberSignedUp,
                command.getMemberName(),
                command.getMemberBirthDate(),
                command.getMemberKind()
        ));
    }

    public void handleCommand(EditMember command) {
        if (!memberKind.equals(command.getMemberKind())) {
            sendEvent(MemberEvent.of(
                    getReference(),
                    MemberEventDefinition.MemberKindChanged,
                    command.getMemberName(),
                    command.getMemberBirthDate(),
                    command.getMemberKind()
            ));
        }
        if (!(memberName.equals(command.getMemberName()) &&
                memberBirthDate.equals(command.getMemberBirthDate()))) {
            sendEvent(MemberEvent.of(
                    getReference(),
                    MemberEventDefinition.MemberEdited,
                    command.getMemberName(),
                    command.getMemberBirthDate(),
                    command.getMemberKind()
            ));
        }
    }

    public void handleCommand(QuitMember command) {
        sendEvent(MemberEvent.of(
                getReference(),
                MemberEventDefinition.MemberQuited,
                this.memberName,
                this.memberBirthDate,
                this.memberKind
        ));
    }

    public void handleCommand(ChangeMemberKind changeMemberKind) {
        sendEvent(MemberEvent.of(
                getReference(),
                MemberEventDefinition.MemberKindChanged,
                this.memberName,
                this.memberBirthDate,
                changeMemberKind.getMemberKind()
        ));
    }
}
