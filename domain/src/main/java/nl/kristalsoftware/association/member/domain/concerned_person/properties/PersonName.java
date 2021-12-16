package nl.kristalsoftware.association.member.domain.concerned_person.properties;

import lombok.Getter;
import nl.kristalsoftware.domain.base.TinyStringType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

@ValueObject
public class PersonName extends TinyStringType {

    @Getter
    final private String firstName;

    @Getter
    final private String lastName;

    private PersonName(String firstName, String lastName) {
        super(firstName + " " + lastName);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static PersonName of(String firstName, String lastName) {
        return new PersonName(firstName, lastName);
    }

}
