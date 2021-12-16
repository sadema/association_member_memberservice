package nl.kristalsoftware.association.member.domain.concerned_person.properties;

import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.domain.base.TinyType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

@Slf4j
@ValueObject
public class PersonRole extends TinyType<Role> {

    protected PersonRole(Role role) {
        super(role);
    }

    public static PersonRole of(Role value) {
        return new PersonRole(value);
    }

    public static PersonRole of(String value) {
        if (value != null) {
            return new PersonRole(getRole(value));
        }
        return new PersonRole(null);
    }

    private static Role getRole(String value) {
        try {
            Role role = Role.valueOf(value);
            return role;
        }
        catch (IllegalArgumentException iae) {
            log.error(iae.getMessage());
            return null;
        }
    }

    @Override
    public Boolean isEmpty() {
        return false;
    }

}
