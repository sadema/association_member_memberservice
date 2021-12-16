package nl.kristalsoftware.association.member.rest.address;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.address.AddressService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class AddressController {

    @Value("${member.datastore.viewstore.url}")
    private String url;

    private final AddressService addressService;

//    @GetMapping(value = "/address")
//    public ResponseEntity<AddressData> getAddressReference(
//            @RequestParam String zipCode,
//            @RequestParam String streetNumber) {
//        Optional<Address> optionalAddress = addressService.getAddressByZipcodeAndStreetnumber(zipCode, streetNumber);
//        if (optionalAddress.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return ResponseEntity.ok(AddressData.of(optionalAddress.get()));
//    }

//    @PostMapping(value = "/addresses", consumes = "application/json")
//    public ResponseEntity<Void> addAddress(@RequestBody AddressData addressData) {
//        AddressReference addressReference = addressService.addAddress(CompoundAddress.of(
//                        Street.of(addressData.getStreet()),
//                        StreetNumber.of(addressData.getStreetNumber()),
//                        City.of(addressData.getCity()),
//                        ZipCode.of(addressData.getZipCode())
//                )
//        );
//        return ResponseEntity.created(URI.create("/addresses/" + addressReference.getStringValue())).build();
//    }

//    @PutMapping(value = "/addresses/{addressReference}", consumes = "application/json")
//    public ResponseEntity<Void> editAddress(@RequestBody AddressData addressData, @PathVariable String addressReference) {
//        try {
//            addressService.editAddress(
//                    AddressReference.of(UUID.fromString(addressReference)),
//                    CompoundAddress.of(
//                            Street.of(addressData.getStreet()),
//                            StreetNumber.of(addressData.getStreetNumber()),
//                            City.of(addressData.getCity()),
//                            ZipCode.of(addressData.getZipCode())
//                    )
//            );
//        } catch (PropertiesNotChangedException e) {
//            ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
//        }
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }

//    @DeleteMapping(value = "/addresses/{addressReference}")
//    public ResponseEntity<Void> quitMember(@PathVariable String addressReference) {
//        addressService.quitAddress(
//                AddressReference.of(UUID.fromString(addressReference))
//        );
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }

}
