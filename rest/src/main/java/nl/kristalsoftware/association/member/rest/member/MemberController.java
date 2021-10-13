package nl.kristalsoftware.association.member.rest.member;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.member.MemberAddress;
import nl.kristalsoftware.association.member.domain.member.MemberBirthDate;
import nl.kristalsoftware.association.member.domain.member.MemberCity;
import nl.kristalsoftware.association.member.domain.member.MemberName;
import nl.kristalsoftware.association.member.domain.member.MemberReference;
import nl.kristalsoftware.association.member.domain.member.MemberService;
import nl.kristalsoftware.association.member.domain.member.MemberZipCode;
import nl.kristalsoftware.domain.base.PropertiesNotChangedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

@RequiredArgsConstructor
@RestController
public class MemberController {

    @Value("${team.datastore.viewstore.url}")
    private String url;

    private final MemberService memberService;

    @PostMapping(value = "/members", consumes = "application/json")
    public ResponseEntity<Void> createMember(@RequestBody MemberData memberData) {
        MemberReference memberReference = memberService.registerMember(
                memberData.getFirstName(),
                memberData.getLastName(),
                memberData.getBirthDate(),
                memberData.getAddress(),
                memberData.getZip(),
                memberData.getCity()
        );
        return ResponseEntity.created(URI.create(url + memberReference.getValue().toString())).build();
    }

    @PutMapping(value = "/members/{memberReference}", consumes = "application/json")
    public ResponseEntity<Void> editTeam(@RequestBody MemberData memberData, @PathVariable String memberReference) {
        try {
            memberService.editMember(
                    MemberReference.of(UUID.fromString(memberReference)),
                    MemberName.of(memberData.getFirstName(), memberData.getLastName()),
                    MemberBirthDate.of(memberData.getBirthDate()),
                    MemberAddress.of(memberData.getAddress()),
                    MemberCity.of(memberData.getCity()),
                    MemberZipCode.of(memberData.getZip())
            );
        } catch (PropertiesNotChangedException e) {
            ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
