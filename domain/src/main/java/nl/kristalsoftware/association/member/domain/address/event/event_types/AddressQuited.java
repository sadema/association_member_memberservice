package nl.kristalsoftware.association.member.domain.address.event.event_types;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.address.properties.AddressReference;
import nl.kristalsoftware.domain.base.BaseEvent;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class AddressQuited implements BaseEvent {

    private final AddressReference addressReference;

}
