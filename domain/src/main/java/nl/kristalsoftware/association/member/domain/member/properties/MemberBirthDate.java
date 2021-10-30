package nl.kristalsoftware.association.member.domain.member.properties;

import nl.kristalsoftware.domain.base.TinyDateType;

import java.time.LocalDate;

public class MemberBirthDate extends TinyDateType {

    private MemberBirthDate(LocalDate localDate) {
        super(localDate);
    }

    public static MemberBirthDate of(LocalDate localDate) {
        return new MemberBirthDate(localDate);
    }

    public static MemberBirthDate of(Long dateInMillis) {
        return new MemberBirthDate(TinyDateType.getLocalDateFromMillis(dateInMillis));
    }
}
