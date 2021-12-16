package nl.kristalsoftware.association.member.domain.address.event.event_types;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.address.properties.City;
import nl.kristalsoftware.association.member.domain.address.properties.Street;
import nl.kristalsoftware.association.member.domain.address.properties.StreetNumber;
import nl.kristalsoftware.association.member.domain.address.properties.ZipCode;
import nl.kristalsoftware.domain.base.BaseEvent;

@Getter
@RequiredArgsConstructor(staticName = "of")
public class AddressEdited implements BaseEvent {

    private final ZipCode zipCode;

    private final StreetNumber streetNumber;

    private final Street street;

    private final City city;

}
