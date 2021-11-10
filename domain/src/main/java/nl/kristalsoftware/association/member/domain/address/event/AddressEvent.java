package nl.kristalsoftware.association.member.domain.address.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.kristalsoftware.association.member.domain.address.properties.AddressReference;
import nl.kristalsoftware.association.member.domain.address.properties.City;
import nl.kristalsoftware.association.member.domain.address.properties.Street;
import nl.kristalsoftware.association.member.domain.address.properties.StreetNumber;
import nl.kristalsoftware.association.member.domain.address.properties.ZipCode;
import nl.kristalsoftware.domain.base.BaseEvent;

@Data
@AllArgsConstructor(staticName = "of")
public class AddressEvent implements BaseEvent {

    private AddressReference addressReference;

    private AddressEventDefinition domainEventName;

    private Street street;

    private StreetNumber streetNumber;

    private City city;

    private ZipCode zipCode;

}
