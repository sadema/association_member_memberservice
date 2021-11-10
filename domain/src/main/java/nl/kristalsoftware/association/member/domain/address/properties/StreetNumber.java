package nl.kristalsoftware.association.member.domain.address.properties;

import nl.kristalsoftware.domain.base.TinyStringType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

@ValueObject
public class StreetNumber extends TinyStringType {

    private StreetNumber(String value) {
        super(value);
    }

    public static StreetNumber of(String city) {
        return new StreetNumber(city);
    }

}
