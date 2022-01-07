package nl.kristalsoftware.association.member.datastore.types.address.viewstore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.datastore.types.member.viewstore.MemberDocument;
import nl.kristalsoftware.association.member.domain.address.event.event_types.AddressEdited;
import nl.kristalsoftware.datastore.base.viewstore.BaseCouchDBViewStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
public class AddressViewStore extends BaseCouchDBViewStore<MemberDocument> {

    public AddressViewStore(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Value("${member.datastore.viewstore.url}")
    private String memberDatabaseUrl;

    public void memberSignedUp(MemberEventData eventData) {
        String url = memberDatabaseUrl + eventData.getReference();
        MemberDocument memberDocument = MemberDocument.of(eventData);
        createOrUpdateDocument(url, memberDocument);
    }

    public void addressEdited(AddressEdited addressEdited) {
        String key = String.format("key=[\"%s\",\"%s\"]", addressEdited.getZipCode().getValue(), addressEdited.getStreetNumber().getValue());
        String viewUrl = memberDatabaseUrl + "_design/member/_view/addressByZipCodeAndStreetNumber?" + key;
        ResponseEntity<DocumentsByZipCodeAndStreetNumberResult> response = getRestTemplate().getForEntity(viewUrl, DocumentsByZipCodeAndStreetNumberResult.class);
        for (MemberDocumentResult result : response.getBody().rows) {
            String docUrl = memberDatabaseUrl + result.id;
            MemberDocument memberDocument = getDocument(docUrl, MemberDocument.class);
            Optional<AddressDocumentPart> optionalAddressDocumentPart = memberDocument.getAddresses().stream()
                    .filter(it -> it.getZipCode().equals(addressEdited.getZipCode().getValue()))
                    .filter(it -> it.getStreetNumber().equals(addressEdited.getStreetNumber().getValue()))
                    .findFirst();
            optionalAddressDocumentPart.ifPresent(it -> {
                it.setStreet(addressEdited.getStreet().getValue());
                it.setCity(addressEdited.getCity().getValue());
            });
            createOrUpdateDocument(docUrl, memberDocument);
        }
    }

    @NoArgsConstructor
    private static class DocumentsByZipCodeAndStreetNumberResult {

        @Getter
        @Setter
        private List<MemberDocumentResult> rows = new ArrayList<>();

    }

    @NoArgsConstructor
    private static class MemberDocumentResult {

        @Getter
        @Setter
        private UUID id;

    }
}
