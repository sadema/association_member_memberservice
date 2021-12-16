package nl.kristalsoftware.association.member.domain.address.event;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.kristalsoftware.association.member.domain.address.properties.AddressReference;
import nl.kristalsoftware.association.member.domain.address.properties.CompoundAddress;
import nl.kristalsoftware.domain.base.BaseEvent;

@Data
@AllArgsConstructor(staticName = "of")
public class AddressEvent implements BaseEvent {

    private AddressReference addressReference;

    private AddressEventDefinition domainEventName;

    private CompoundAddress address;

}
