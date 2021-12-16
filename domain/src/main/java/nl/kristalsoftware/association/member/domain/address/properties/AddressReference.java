package nl.kristalsoftware.association.member.domain.address.properties;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.domain.base.TinyType;
import nl.kristalsoftware.domain.base.annotations.ValueObject;

@ValueObject
public class AddressReference extends TinyType<AddressReference.ZipCodeStreetNumber> {

    private AddressReference(ZipCode zipCode, StreetNumber streetNumber) {
        super(ZipCodeStreetNumber.of(zipCode, streetNumber));
    }

    public static AddressReference of(ZipCode zipCode, StreetNumber streetNumber) {
        return new AddressReference(zipCode, streetNumber);
    }

    @Override
    public Boolean isEmpty() {
        return getValue().getZipCode().isEmpty() && getValue().getStreetNumber().isEmpty();
    }

    public ZipCode getZipCode() {
        return getValue().getZipCode();
    }

    public StreetNumber getStreetNumber() {
        return getValue().getStreetNumber();
    }

    @EqualsAndHashCode
    @Getter
    @RequiredArgsConstructor(staticName = "of")
    public static class ZipCodeStreetNumber {

        private final ZipCode zipCode;

        private final StreetNumber streetNumber;

    }

}
