package nl.kristalsoftware.association.member.domain.address.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.kristalsoftware.association.member.domain.address.properties.CompoundAddress;

@Data
@AllArgsConstructor(staticName = "of")
public class AssignAddress {

    private CompoundAddress address;

}
