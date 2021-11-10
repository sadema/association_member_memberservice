package nl.kristalsoftware.association.member.domain.address.command;

import lombok.AllArgsConstructor;
import lombok.Data;
import nl.kristalsoftware.association.member.domain.address.properties.City;
import nl.kristalsoftware.association.member.domain.address.properties.Street;
import nl.kristalsoftware.association.member.domain.address.properties.StreetNumber;
import nl.kristalsoftware.association.member.domain.address.properties.ZipCode;

@Data
@AllArgsConstructor(staticName = "of")
public class EditAddress {

    private Street street;

    private StreetNumber streetNumber;

    private City city;

    private ZipCode zipCode;

}
