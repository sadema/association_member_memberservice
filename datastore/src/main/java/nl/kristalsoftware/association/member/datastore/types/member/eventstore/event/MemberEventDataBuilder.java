package nl.kristalsoftware.association.member.datastore.types.member.eventstore.event;

import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.domain.member.event.member_edited.MemberEdited;
import nl.kristalsoftware.association.member.domain.member.event.member_signed_up.MemberSignedUp;
import org.springframework.stereotype.Component;

@Component
public class MemberEventDataBuilder {

    public MemberEventData build(MemberSignedUp event) {
        return MemberEventData.newBuilder()
                .setDomainEventName(event.getClass().getSimpleName())
                .setReference(event.getMemberReference().getStringValue())
                .setFirstName(event.getMemberName().getFirstName())
                .setLastName(event.getMemberName().getLastName())
                .setBirthDate(event.getMemberBirthDate().getDateInMillis())
                .setAddress(event.getMemberAddress().getValue())
                .setCity(event.getMemberCity().getValue())
                .setZip(event.getMemberZipCode().getValue())
                .build();
    }

    public MemberEventData build(MemberEdited event) {
        return MemberEventData.newBuilder()
                .setDomainEventName(event.getClass().getSimpleName())
                .setReference(event.getMemberReference().getStringValue())
                .setFirstName(event.getMemberName().getFirstName())
                .setLastName(event.getMemberName().getLastName())
                .setBirthDate(event.getMemberBirthDate().getDateInMillis())
                .setAddress(event.getMemberAddress().getValue())
                .setCity(event.getMemberCity().getValue())
                .setZip(event.getMemberZipCode().getValue())
                .build();
    }
}
