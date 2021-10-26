package nl.kristalsoftware.association.member.domain.member;

import lombok.Getter;
import nl.kristalsoftware.association.member.domain.member.command.EditMember;
import nl.kristalsoftware.association.member.domain.member.command.SignUpMember;
import nl.kristalsoftware.association.member.domain.member.event.member_edited.MemberEdited;
import nl.kristalsoftware.association.member.domain.member.event.member_signed_up.MemberSignedUp;
import nl.kristalsoftware.domain.base.Aggregate;
import nl.kristalsoftware.domain.base.BaseAggregateRoot;
import nl.kristalsoftware.domain.base.annotations.AggregateRoot;
import org.springframework.context.ApplicationEventPublisher;

@Getter
@AggregateRoot
public class Member extends BaseAggregateRoot<MemberReference> implements Aggregate {

    private MemberName memberName;

    private MemberBirthDate memberBirthDate;

    private MemberAddress memberAddress;

    private MemberCity memberCity;

    private MemberZipCode memberZipCode;

    private Member(MemberReference reference, ApplicationEventPublisher eventPublisher) {
        super(reference, eventPublisher);
    }

    public static Member of(MemberReference reference, ApplicationEventPublisher eventPublisher) {
        return new Member(reference, eventPublisher);
    }

    public void loadData(MemberSignedUp memberSignedUp) {
        memberName = memberSignedUp.getMemberName();
        memberBirthDate = memberSignedUp.getMemberBirthDate();
        memberAddress = memberSignedUp.getMemberAddress();
        memberCity = memberSignedUp.getMemberCity();
        memberZipCode = memberSignedUp.getMemberZipCode();
    }

    public void loadData(MemberEdited memberEdited) {
        memberName = memberEdited.getMemberName();
        memberBirthDate = memberEdited.getMemberBirthDate();
        memberAddress = memberEdited.getMemberAddress();
        memberCity = memberEdited.getMemberCity();
        memberZipCode = memberEdited.getMemberZipCode();
    }

    public void handleCommand(SignUpMember command) {
        sendEvent(MemberSignedUp.of(
                getReference(),
                command.getMemberName(),
                command.getMemberBirthDate(),
                command.getMemberAddress(),
                command.getMemberCity(),
                command.getMemberZipCode()
                ));
    }

    public void handleCommand(EditMember command) {
        sendEvent(MemberEdited.of(
                getReference(),
                command.getMemberName(),
                command.getMemberBirthDate(),
                command.getMemberAddress(),
                command.getMemberCity(),
                command.getMemberZipCode()
        ));
    }
}
