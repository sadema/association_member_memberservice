package nl.kristalsoftware.association.member.domain.member;

import nl.kristalsoftware.domain.base.TinyStringType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

@ValueObject
public class MemberZipCode extends TinyStringType {

    private MemberZipCode(String value) {
        super(value);
    }

    public static MemberZipCode of(String zipCode) {
        return new MemberZipCode(zipCode);
    }

}
