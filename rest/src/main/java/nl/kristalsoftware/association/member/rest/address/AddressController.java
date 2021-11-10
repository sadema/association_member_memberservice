package nl.kristalsoftware.association.member.rest.address;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.address.AddressService;
import nl.kristalsoftware.association.member.domain.address.properties.AddressReference;
import nl.kristalsoftware.association.member.domain.address.properties.City;
import nl.kristalsoftware.association.member.domain.address.properties.Street;
import nl.kristalsoftware.association.member.domain.address.properties.StreetNumber;
import nl.kristalsoftware.association.member.domain.address.properties.ZipCode;
import nl.kristalsoftware.domain.base.PropertiesNotChangedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class AddressController {

    @Value("${member.datastore.viewstore.url}")
    private String url;

    private final AddressService addressService;

    @GetMapping(value = "/address")
    public ResponseEntity<UUID> getAddressReference(
            @RequestParam String zipCode,
            @RequestParam String streetNumber) {
        Optional<UUID> addressUUIDOptional = addressService.getAddressReference(zipCode, streetNumber);
        if (addressUUIDOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(addressUUIDOptional.get());
    }

    @PostMapping(value = "/addresses", consumes = "application/json")
    public ResponseEntity<Void> addAddress(@RequestBody AddressData addressData) {
        AddressReference addressReference = addressService.addAddress(
                Street.of(addressData.getStreet()),
                StreetNumber.of(addressData.getStreetNumber()),
                City.of(addressData.getCity()),
                ZipCode.of(addressData.getZipCode())
        );
        return ResponseEntity.noContent().build();
    }

    @PutMapping(value = "/addresses/{addressReference}", consumes = "application/json")
    public ResponseEntity<Void> editAddress(@RequestBody AddressData addressData, @PathVariable String addressReference) {
        try {
            addressService.editAddress(
                    AddressReference.of(UUID.fromString(addressReference)),
                    Street.of(addressData.getStreet()),
                    StreetNumber.of(addressData.getStreetNumber()),
                    City.of(addressData.getCity()),
                    ZipCode.of(addressData.getZipCode())
            );
        } catch (PropertiesNotChangedException e) {
            ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(value = "/addresses/{addressReference}")
    public ResponseEntity<Void> quitMember(@PathVariable String addressReference) {
        addressService.quitAddress(
                AddressReference.of(UUID.fromString(addressReference))
        );
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
