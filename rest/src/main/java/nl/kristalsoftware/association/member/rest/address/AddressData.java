package nl.kristalsoftware.association.member.rest.address;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@NoArgsConstructor
@Data
public class AddressData {

    private String zipCode = "";

    private String streetNumber = "";

    private String street = "";

    private String city = "";

    private UUID memberReference;

//    public static AddressData of(Address address) {
//        return new AddressData(
//                address.getStreet().getValue(),
//                address.getStreetNumber().getValue(),
//                address.getCity().getValue(),
//                address.getZipCode().getValue()
//        );
//    }

}
