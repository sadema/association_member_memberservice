package nl.kristalsoftware.association.member.domain.member.properties;

import lombok.Getter;
import nl.kristalsoftware.domain.base.TinyStringType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

@ValueObject
public class MemberName extends TinyStringType {

    @Getter
    final private String firstName;

    @Getter
    final private String lastName;

    private MemberName(String firstName, String lastName) {
        super(firstName + " " + lastName);
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public static MemberName of(String firstName, String lastName) {
        return new MemberName(firstName, lastName);
    }

}
