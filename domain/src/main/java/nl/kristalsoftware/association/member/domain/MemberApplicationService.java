package nl.kristalsoftware.association.member.domain;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import nl.kristalsoftware.association.member.domain.address.AddressService;
import nl.kristalsoftware.association.member.domain.address.properties.CompoundAddress;
import nl.kristalsoftware.association.member.domain.member.MemberService;
import nl.kristalsoftware.association.member.domain.member.properties.MemberBirthDate;
import nl.kristalsoftware.association.member.domain.member.properties.MemberKind;
import nl.kristalsoftware.association.member.domain.member.properties.MemberName;
import nl.kristalsoftware.association.member.domain.member.properties.MemberReference;
import nl.kristalsoftware.domain.base.annotations.DomainService;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@DomainService
@Service
public class MemberApplicationService {

    private final MemberService memberService;

    private final AddressService addressService;

    public void editMember(MemberReference memberReference,
                           MemberName memberName,
                           MemberBirthDate memberBirthDate,
                           MemberKind memberKind,
                           List<CompoundAddress> memberAddresses) {
        addressService.processAddresses(memberAddresses, memberReference);
        memberService.editMember(memberReference, memberName, memberBirthDate, memberKind, memberAddresses);
    }

}
