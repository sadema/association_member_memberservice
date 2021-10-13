package nl.kristalsoftware.association.member.domain.member;

import lombok.Getter;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.domain.member.command.EditMember;
import nl.kristalsoftware.association.member.domain.member.command.SignUpMember;
import nl.kristalsoftware.association.member.domain.member.event.member_edited.MemberEdited;
import nl.kristalsoftware.association.member.domain.member.event.member_edited.MemberEditedEventData;
import nl.kristalsoftware.association.member.domain.member.event.member_signed_up.MemberSignedUp;
import nl.kristalsoftware.association.member.domain.member.event.member_signed_up.MemberSignedUpEventData;
import nl.kristalsoftware.domain.base.Aggregate;
import nl.kristalsoftware.domain.base.BaseAggregateRoot;
import nl.kristalsoftware.domain.base.BaseEvent;
import nl.kristalsoftware.domain.base.annotations.AggregateRoot;
import org.springframework.context.ApplicationEventPublisher;

@Getter
@AggregateRoot
public class Member extends BaseAggregateRoot<MemberReference, BaseEvent<MemberEventData>> implements Aggregate {

    private MemberName memberName;

    private MemberBirthDate memberBirthDate;

    private MemberAddress memberAddress;

    private MemberCity memberCity;

    private MemberZipCode memberZipCode;

    public Member(MemberReference reference, ApplicationEventPublisher eventPublisher) {
        super(reference, eventPublisher);
    }

    public void loadData(MemberSignedUpEventData memberSignedUpEventData) {
        memberName = memberSignedUpEventData.getMemberName();
        memberBirthDate = memberSignedUpEventData.getMemberBirthDate();
        memberAddress = memberSignedUpEventData.getMemberAddress();
        memberCity = memberSignedUpEventData.getMemberCity();
        memberZipCode = memberSignedUpEventData.getMemberZipCode();
    }

    public void loadData(MemberEditedEventData memberSignedUpEventData) {
        memberName = memberSignedUpEventData.getMemberName();
        memberBirthDate = memberSignedUpEventData.getMemberBirthDate();
        memberAddress = memberSignedUpEventData.getMemberAddress();
        memberCity = memberSignedUpEventData.getMemberCity();
        memberZipCode = memberSignedUpEventData.getMemberZipCode();
    }

    public void handleCommand(SignUpMember command) {
        MemberEventData memberEventData = MemberEventData.newBuilder()
                .setFirstName(command.getFirstName())
                .setLastName(command.getLastName())
                .setBirthDate(command.getBirthDate())
                .setAddress(command.getAddress())
                .setCity(command.getCity())
                .setZip(command.getZipCode())
                .build();
        sendEvent(new MemberSignedUp(memberEventData));
    }

    public void handleCommand(EditMember command) {
        MemberEventData memberEventData = MemberEventData.newBuilder()
                .setFirstName(command.getMemberName().getFirstName())
                .setLastName(command.getMemberName().getLastName())
                .setBirthDate(command.getMemberBirthDate().getDateInMillis())
                .setAddress(command.getMemberAddress().getValue())
                .setCity(command.getMemberCity().getValue())
                .setZip(command.getMemberZipCode().getValue())
                .build();
        sendEvent(new MemberEdited(memberEventData));
    }
}
