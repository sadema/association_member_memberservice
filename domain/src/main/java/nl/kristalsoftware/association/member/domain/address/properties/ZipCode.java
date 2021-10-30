package nl.kristalsoftware.association.member.domain.address.properties;

import nl.kristalsoftware.domain.base.TinyStringType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

@ValueObject
public class ZipCode extends TinyStringType {

    private ZipCode(String value) {
        super(value);
    }

    public static ZipCode of(String zipCode) {
        return new ZipCode(zipCode);
    }

}
