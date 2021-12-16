package nl.kristalsoftware.association.member.datastore.types.member.viewstore;

import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.MemberEventData;
import nl.kristalsoftware.association.member.datastore.types.address.viewstore.AddressDocumentPart;
import nl.kristalsoftware.datastore.base.viewstore.BaseCouchDBViewStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Slf4j
@Component
public class MemberViewStore extends BaseCouchDBViewStore<MemberDocument> {

    public MemberViewStore(RestTemplate restTemplate) {
        super(restTemplate);
    }

    @Value("${member.datastore.viewstore.url}")
    private String memberDatabaseUrl;

    public void memberSignedUp(MemberEventData eventData) {
        String url = memberDatabaseUrl + eventData.getReference();
        MemberDocument memberDocument = MemberDocument.of(eventData);
        createOrUpdateDocument(url, memberDocument);
    }

    public void memberEdited(MemberEventData eventData) {
        String url = memberDatabaseUrl + eventData.getReference();
        MemberDocument memberDocument = getDocument(url, MemberDocument.class);
        memberDocument.setFirstName(eventData.getFirstName());
        memberDocument.setLastName(eventData.getLastName());
        memberDocument.setBirthDate(eventData.getBirthDate().toEpochMilli());
        createOrUpdateDocument(url, memberDocument);
    }

    public void memberKindChanged(MemberEventData eventData) {
        String url = memberDatabaseUrl + eventData.getReference();
        MemberDocument memberDocument = getDocument(url, MemberDocument.class);
        memberDocument.setKind(eventData.getKind());
        createOrUpdateDocument(url, memberDocument);
    }

    public void memberQuited(MemberEventData eventData) {
        String url = memberDatabaseUrl + eventData.getReference();
        MemberDocument memberDocument = getDocument(url, MemberDocument.class);
        deleteDocument(url + "?rev=" + memberDocument.get_rev());
    }

    public void memberAddressAssigned(MemberEventData eventData) {
        String memberUrl = memberDatabaseUrl + eventData.getReference();
        MemberDocument memberDocument = getDocument(memberUrl, MemberDocument.class);
        memberDocument.getAddresses().add(AddressDocumentPart.of(eventData));
        createOrUpdateDocument(memberUrl, memberDocument);
    }

    public void memberAddressUnAssigned(MemberEventData eventData) {
        String memberUrl = memberDatabaseUrl + eventData.getReference();
        MemberDocument memberDocument = getDocument(memberUrl, MemberDocument.class);
        log.info("ZipCode: {} StreetNumber: {}", eventData.getAddress().getZipCode(), eventData.getAddress().getStreetNumber());
        log.info("Document: {}", memberDocument);
        Optional<AddressDocumentPart> optionalRemovedAddress = memberDocument.getAddresses().stream()
                .filter(it -> it.getZipCode().equals(eventData.getAddress().getZipCode()))
                .filter(it -> it.getStreetNumber().equals(eventData.getAddress().getStreetNumber()))
                .findFirst();
        optionalRemovedAddress.ifPresent(it -> {
            memberDocument.getAddresses().remove(it);
            createOrUpdateDocument(memberUrl, memberDocument);
        });
    }
}
