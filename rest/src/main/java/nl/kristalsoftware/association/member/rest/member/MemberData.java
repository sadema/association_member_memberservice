package nl.kristalsoftware.association.member.rest.member;

import lombok.Data;

@Data
public class MemberData {

    private String firstName = "";

    private String lastName = "";

    private Long birthDate = 0l;

    private String state = "PLAYER";

}
