package nl.kristalsoftware.association.member.domain.member;

import nl.kristalsoftware.domain.base.TinyStringType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

@ValueObject
public class MemberAddress extends TinyStringType {

    private MemberAddress(String value) {
        super(value);
    }

    public static MemberAddress of(String address) {
        return new MemberAddress(address);
    }

}
