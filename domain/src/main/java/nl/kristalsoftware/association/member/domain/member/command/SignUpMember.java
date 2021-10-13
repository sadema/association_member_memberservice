package nl.kristalsoftware.association.member.domain.member.command;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor(staticName = "of")
public class SignUpMember {

    private String firstName;

    private String lastName;

    private Long birthDate;

    private String address;

    private String zipCode;

    private String city;

}
