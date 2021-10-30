package nl.kristalsoftware.association.member.domain.address.properties;

import nl.kristalsoftware.domain.base.TinyStringType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

@ValueObject
public class Street extends TinyStringType {

    private Street(String value) {
        super(value);
    }

    public static Street of(String street) {
        return new Street(street);
    }

}
