package nl.kristalsoftware.association.member.domain.member.properties;

import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.domain.base.TinyType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

@Slf4j
@ValueObject
public class MemberKind extends TinyType<Kind> {

    protected MemberKind(Kind kind) {
        super(kind);
    }

    public static MemberKind of(Kind value) {
        return new MemberKind(value);
    }

    public static MemberKind of(String value) {
        if (value != null) {
            return new MemberKind(getState(value));
        }
        return new MemberKind(Kind.PLAYER);
    }

    private static Kind getState(String value) {
        try {
            Kind kind = Kind.valueOf(value);
            return kind;
        }
        catch (IllegalArgumentException iae) {
            log.error(iae.getMessage());
            return Kind.PLAYER;
        }
    }

    @Override
    public Boolean isEmpty() {
        return false;
    }

}
