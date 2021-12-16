package nl.kristalsoftware.association.member.domain.member;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.domain.address.properties.AddressReference;
import nl.kristalsoftware.association.member.domain.address.properties.CompoundAddress;
import nl.kristalsoftware.association.member.domain.member.command.EditMember;
import nl.kristalsoftware.association.member.domain.member.command.ProcessMemberAddresses;
import nl.kristalsoftware.association.member.domain.member.command.QuitMember;
import nl.kristalsoftware.association.member.domain.member.command.SignUpMember;
import nl.kristalsoftware.association.member.domain.member.event.MemberEvent;
import nl.kristalsoftware.association.member.domain.member.event.MemberEventDefinition;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberAddressAssigned;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberAddressUnAssigned;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Getter
@AggregateRoot
public class Member extends BaseAggregateRoot<MemberReference,MemberEvent> implements Aggregate<MemberReference> {

    private MemberName memberName;

    private MemberBirthDate memberBirthDate;

    private MemberKind memberKind;

    private List<AddressReference> addressReferences = new ArrayList<>();

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

    public void loadData(MemberAddressAssigned memberAddressAssigned) {
        addressReferences.add(AddressReference.of(memberAddressAssigned.getZipCode(), memberAddressAssigned.getStreetNumber()));
    }

    public void loadData(MemberAddressUnAssigned memberAddressUnAssigned) {
        boolean result = addressReferences.remove(memberAddressUnAssigned.getAddressReference());
        log.info("remove operation: {}", result ? "successfull" : "failed");
    }

    public void handleCommand(SignUpMember command) {
        sendEvent(MemberEvent.of(getReference(),
                MemberEventDefinition.MemberSignedUp,
                command.getMemberName(),
                command.getMemberBirthDate(),
                command.getMemberKind(),
                null
        ));
    }

    public void handleCommand(EditMember command) {
        if (!memberKind.equals(command.getMemberKind())) {
            sendEvent(MemberEvent.of(
                    getReference(),
                    MemberEventDefinition.MemberKindChanged,
                    memberName,
                    memberBirthDate,
                    command.getMemberKind(),
                    null
            ));
        }
        if (!(memberName.equals(command.getMemberName()) &&
                memberBirthDate.equals(command.getMemberBirthDate()))) {
            sendEvent(MemberEvent.of(
                    getReference(),
                    MemberEventDefinition.MemberEdited,
                    command.getMemberName(),
                    command.getMemberBirthDate(),
                    memberKind,
                    null
            ));
        }
    }

    public void handleCommand(QuitMember command) {
        sendEvent(MemberEvent.of(
                getReference(),
                MemberEventDefinition.MemberQuited,
                this.memberName,
                this.memberBirthDate,
                this.memberKind,
                null
        ));
    }

    public void handleCommand(ProcessMemberAddresses processMemberAddresses) {
        List<CompoundAddress> inMemberAddresses = processMemberAddresses.getInAddresses();
        addressReferences.stream()
                .filter(it -> getRemovedAddress(inMemberAddresses, it).isPresent())
                .forEach(it -> {
                    sendEvent(MemberEvent.of(
                            getReference(),
                            MemberEventDefinition.MemberAddressUnAssigned,
                            this.memberName,
                            this.memberBirthDate,
                            this.memberKind,
                            CompoundAddress.of(it.getZipCode(), it.getStreetNumber())
                    ));
                });
        inMemberAddresses.stream()
                .filter(it -> isNewAddress(it))
                .forEach(it -> {
                    sendEvent(MemberEvent.of(
                            getReference(),
                            MemberEventDefinition.MemberAddressAssigned,
                            this.memberName,
                            this.memberBirthDate,
                            this.memberKind,
                            it
                    ));
                });
    }

    private Optional<AddressReference> getRemovedAddress(
            List<CompoundAddress> memberAddresses,
            AddressReference addressReference) {
        if (memberAddresses.stream()
                .anyMatch(it -> addressReference.equals(
                        AddressReference.of(it.getZipCode(), it.getStreetNumber()))
                )
        ) {
            return Optional.empty();
        }
        return Optional.of(addressReference);
    }

    private boolean isNewAddress(CompoundAddress compoundAddress) {
        return !addressReferences.stream()
                .anyMatch(it -> it.equals(
                        AddressReference.of(compoundAddress.getZipCode(), compoundAddress.getStreetNumber())
                    )
                );
    }

}
