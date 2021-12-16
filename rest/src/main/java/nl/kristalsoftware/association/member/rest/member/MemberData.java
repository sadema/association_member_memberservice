package nl.kristalsoftware.association.member.rest.member;

import lombok.Data;
import nl.kristalsoftware.association.member.rest.address.AddressData;

import java.util.List;

@Data
public class MemberData {

    private String firstName = "";

    private String lastName = "";

    private Long birthDate = 0l;

    private String kind = "PLAYER";

    private List<AddressData> addresses;

}
