package nl.kristalsoftware.association.member.domain.address.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

@Getter
@RequiredArgsConstructor(staticName = "of")
@ValueObject
public class CompoundAddress {

    private final ZipCode zipCode;

    private final StreetNumber streetNumber;

    private final Street street;

    private final City city;

    public boolean isEdited(Street street, City city) {
        return !(
                this.street.equals(street) &&
                this.city.equals(city)
        );
    }

    public static CompoundAddress of(ZipCode zipCode, StreetNumber streetNumber) {
        return new CompoundAddress(zipCode, streetNumber, Street.of(""), City.of(""));
    }

}
