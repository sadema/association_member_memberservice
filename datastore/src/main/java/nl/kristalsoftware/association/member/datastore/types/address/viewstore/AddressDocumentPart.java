package nl.kristalsoftware.association.member.datastore.types.address.viewstore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.MemberEventData;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class AddressDocumentPart {

    private String zipCode;

    private String streetNumber;

    private String street;

    private String city;

    public static AddressDocumentPart of(MemberEventData eventData) {
        return new AddressDocumentPart(
                eventData.getAddress().getZipCode(),
                eventData.getAddress().getStreetNumber(),
                eventData.getAddress().getStreet(),
                eventData.getAddress().getCity()
        );
    }

}
