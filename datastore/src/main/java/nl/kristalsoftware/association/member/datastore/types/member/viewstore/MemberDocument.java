package nl.kristalsoftware.association.member.datastore.types.member.viewstore;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.datastore.DocumentType;
import nl.kristalsoftware.association.member.datastore.types.address.viewstore.AddressDocumentPart;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Data
public class MemberDocument {

    private String _id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String _rev;

    private String type;

    private String firstName;

    private String lastName;

    private Long birthDate;

    private String kind;

    private List<AddressDocumentPart> addresses = new ArrayList<>();

    public static MemberDocument of(MemberEventData memberEventData) {
        return new MemberDocument(
                memberEventData.getReference().toString(),
                null,
                DocumentType.MEMBER.toString(),
                memberEventData.getFirstName(),
                memberEventData.getLastName(),
                memberEventData.getBirthDate().toEpochMilli(),
                memberEventData.getKind(),
                new ArrayList<>()
        );
    }

}
