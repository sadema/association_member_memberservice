package nl.kristalsoftware.association.member.domain.concerned_person.properties;

import nl.kristalsoftware.domain.base.TinyUUIDType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

import java.util.UUID;

@ValueObject
public class ConcernedPersonReference extends TinyUUIDType {

    private ConcernedPersonReference(UUID value) {
        super(value);
    }

    public static ConcernedPersonReference of(UUID value) {
        return new ConcernedPersonReference(value);
    }

    public static ConcernedPersonReference of(String value) {
        UUID uuid = null;
        if (value != null && !value.isEmpty() && !value.equals("0")) {
            uuid = UUID.fromString(value);
        }
        return new ConcernedPersonReference(uuid);
    }

}
