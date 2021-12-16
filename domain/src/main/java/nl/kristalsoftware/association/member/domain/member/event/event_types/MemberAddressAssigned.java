package nl.kristalsoftware.association.member.domain.member.event.event_types;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.address.properties.StreetNumber;
import nl.kristalsoftware.association.member.domain.address.properties.ZipCode;
import nl.kristalsoftware.association.member.domain.member.properties.MemberReference;
import nl.kristalsoftware.domain.base.BaseEvent;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class MemberAddressAssigned implements BaseEvent {

    private final MemberReference memberReference;

    private final ZipCode zipCode;

    private final StreetNumber streetNumber;

}
