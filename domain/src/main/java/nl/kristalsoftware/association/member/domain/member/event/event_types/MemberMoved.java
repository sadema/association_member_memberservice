package nl.kristalsoftware.association.member.domain.member.event.event_types;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.address.properties.AddressReference;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class MemberMoved {

    private AddressReference addressReference;

}
