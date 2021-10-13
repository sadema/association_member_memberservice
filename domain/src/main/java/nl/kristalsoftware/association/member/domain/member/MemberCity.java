package nl.kristalsoftware.association.member.domain.member;

import nl.kristalsoftware.domain.base.TinyStringType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

@ValueObject
public class MemberCity extends TinyStringType {

    private MemberCity(String value) {
        super(value);
    }

    public static MemberCity of(String city) {
        return new MemberCity(city);
    }

}
