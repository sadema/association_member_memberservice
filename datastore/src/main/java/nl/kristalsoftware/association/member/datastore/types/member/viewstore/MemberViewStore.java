package nl.kristalsoftware.association.member.datastore.types.member.viewstore;

import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.datastore.types.address.viewstore.AddressDocumentPart;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberAddressAssigned;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberAddressUnAssigned;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberQuited;
import nl.kristalsoftware.association.member.domain.member.event.event_types.MemberSignedUp;
import nl.kristalsoftware.association.member.domain.member.event.member_edited.MemberEdited;
import nl.kristalsoftware.association.member.domain.member.event.member_kind_changed.MemberKindChanged;
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

    public void memberSignedUp(MemberSignedUp memberSignedUp) {
        String url = memberDatabaseUrl + memberSignedUp.getMemberReference().getStringValue();
        MemberDocument memberDocument = MemberDocument.of(memberSignedUp);
        createOrUpdateDocument(url, memberDocument);
    }

    public void memberEdited(MemberEdited memberEdited) {
        String url = memberDatabaseUrl + memberEdited.getMemberReference().getStringValue();
        MemberDocument memberDocument = getDocument(url, MemberDocument.class);
        memberDocument.setFirstName(memberEdited.getMemberName().getFirstName());
        memberDocument.setLastName(memberEdited.getMemberName().getLastName());
        memberDocument.setBirthDate(memberEdited.getMemberBirthDate().getDateInMillis());
        createOrUpdateDocument(url, memberDocument);
    }

    public void memberKindChanged(MemberKindChanged memberKindChanged) {
        String url = memberDatabaseUrl + memberKindChanged.getMemberReference().getStringValue();
        MemberDocument memberDocument = getDocument(url, MemberDocument.class);
        memberDocument.setKind(memberKindChanged.getMemberKind().getValue().name());
        createOrUpdateDocument(url, memberDocument);
    }

    public void memberQuited(MemberQuited eventData) {
        String url = memberDatabaseUrl + eventData.getMemberReference().getStringValue();
        MemberDocument memberDocument = getDocument(url, MemberDocument.class);
        deleteDocument(url + "?rev=" + memberDocument.get_rev());
    }

    public void memberAddressAssigned(MemberAddressAssigned memberAddressAssigned) {
        String memberUrl = memberDatabaseUrl + memberAddressAssigned.getMemberReference().getStringValue();
        MemberDocument memberDocument = getDocument(memberUrl, MemberDocument.class);
        memberDocument.getAddresses().add(
                AddressDocumentPart.of(memberAddressAssigned.getZipCode(),
                        memberAddressAssigned.getStreetNumber(),
                        memberAddressAssigned.getStreet(),
                        memberAddressAssigned.getCity()
                )
        );
        createOrUpdateDocument(memberUrl, memberDocument);
    }

    public void memberAddressUnAssigned(MemberAddressUnAssigned memberAddressUnAssigned) {
        String memberUrl = memberDatabaseUrl + memberAddressUnAssigned.getMemberReference().getStringValue();
        MemberDocument memberDocument = getDocument(memberUrl, MemberDocument.class);
        log.info("ZipCode: {} StreetNumber: {}", memberAddressUnAssigned.getAddressReference().getZipCode(), memberAddressUnAssigned.getAddressReference().getStreetNumber());
        log.info("Document: {}", memberDocument);
        Optional<AddressDocumentPart> optionalRemovedAddress = memberDocument.getAddresses().stream()
                .filter(it -> it.getZipCode().equals(memberAddressUnAssigned.getAddressReference().getZipCode().getValue()))
                .filter(it -> it.getStreetNumber().equals(memberAddressUnAssigned.getAddressReference().getStreetNumber().getValue()))
                .findFirst();
        optionalRemovedAddress.ifPresent(it -> {
            memberDocument.getAddresses().remove(it);
            createOrUpdateDocument(memberUrl, memberDocument);
        });
    }
}
