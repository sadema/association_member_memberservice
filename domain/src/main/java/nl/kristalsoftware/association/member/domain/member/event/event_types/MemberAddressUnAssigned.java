package nl.kristalsoftware.association.member.domain.member.event.event_types;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.address.properties.AddressReference;
import nl.kristalsoftware.association.member.domain.member.properties.MemberReference;
import nl.kristalsoftware.domain.base.BaseEvent;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class MemberAddressUnAssigned implements BaseEvent {

    private final MemberReference memberReference;

    private final AddressReference addressReference;

}
