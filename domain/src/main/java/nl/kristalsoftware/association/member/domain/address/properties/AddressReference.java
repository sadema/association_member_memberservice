package nl.kristalsoftware.association.member.domain.address.properties;

import nl.kristalsoftware.domain.base.TinyUUIDType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

import java.util.UUID;

@ValueObject
public class AddressReference extends TinyUUIDType {

    private AddressReference(UUID value) {
        super(value);
    }

    public static AddressReference of(UUID value) {
        return new AddressReference(value);
    }

    public static AddressReference of(String value) {
        UUID uuid = null;
        if (value != null && !value.isEmpty() && !value.equals("0")) {
            uuid = UUID.fromString(value);
        }
        return new AddressReference(uuid);
    }

}
