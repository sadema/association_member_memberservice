package nl.kristalsoftware.association.member.domain.member.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.kristalsoftware.association.member.domain.address.properties.CompoundAddress;

import java.util.List;

@Data
@AllArgsConstructor(staticName = "of")
public class ProcessMemberAddresses {

    private List<CompoundAddress> inAddresses;

}
