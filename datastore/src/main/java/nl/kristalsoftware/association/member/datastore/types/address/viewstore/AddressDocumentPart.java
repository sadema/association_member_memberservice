package nl.kristalsoftware.association.member.datastore.types.address.viewstore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.domain.address.properties.City;
import nl.kristalsoftware.association.member.domain.address.properties.Street;
import nl.kristalsoftware.association.member.domain.address.properties.StreetNumber;
import nl.kristalsoftware.association.member.domain.address.properties.ZipCode;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class AddressDocumentPart {

    private String zipCode;

    private String streetNumber;

    private String street;

    private String city;

    public static AddressDocumentPart of(ZipCode zipCode,
                                         StreetNumber streetNumber,
                                         Street street,
                                         City city) {
        return new AddressDocumentPart(
                zipCode.getValue(),
                streetNumber.getValue(),
                street.getValue(),
                city.getValue()
        );
    }

}
