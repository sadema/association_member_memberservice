package nl.kristalsoftware.association.member.domain.address.properties;

import nl.kristalsoftware.domain.base.TinyStringType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

@ValueObject
public class City extends TinyStringType {

    private City(String value) {
        super(value);
    }

    public static City of(String city) {
        return new City(city);
    }

}
