package nl.kristalsoftware.association.member.domain.member;

import nl.kristalsoftware.domain.base.TinyUUIDType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

import java.util.UUID;

@ValueObject
public class MemberReference extends TinyUUIDType {

    private MemberReference(UUID value) {
        super(value);
    }

    public static MemberReference of(UUID value) {
        return new MemberReference(value);
    }

    public static MemberReference of(String value) {
        UUID uuid = null;
        if (value != null && !value.isEmpty() && !value.equals("0")) {
            uuid = UUID.fromString(value);
        }
        return new MemberReference(uuid);
    }

}
