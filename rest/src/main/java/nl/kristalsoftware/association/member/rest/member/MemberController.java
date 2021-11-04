package nl.kristalsoftware.association.member.rest.member;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.member.MemberService;
import nl.kristalsoftware.association.member.domain.member.properties.MemberBirthDate;
import nl.kristalsoftware.association.member.domain.member.properties.MemberKind;
import nl.kristalsoftware.association.member.domain.member.properties.MemberName;
import nl.kristalsoftware.association.member.domain.member.properties.MemberReference;
import nl.kristalsoftware.domain.base.PropertiesNotChangedException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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

    @Value("${member.datastore.viewstore.url}")
    private String url;

    private final MemberService memberService;

    @PostMapping(value = "/members", consumes = "application/json")
    public ResponseEntity<Void> signUpMember(@RequestBody MemberData memberData) {
        MemberReference memberReference = memberService.signUpMember(
                MemberName.of(memberData.getFirstName(), memberData.getLastName()),
                MemberBirthDate.of(memberData.getBirthDate()),
                MemberKind.of(memberData.getKind())
        );
        return ResponseEntity.created(URI.create(url + memberReference.getValue().toString())).build();
    }

    @PutMapping(value = "/members/{memberReference}", consumes = "application/json")
    public ResponseEntity<Void> editMember(@RequestBody MemberData memberData, @PathVariable String memberReference) {
        try {
            memberService.editMember(
                    MemberReference.of(UUID.fromString(memberReference)),
                    MemberName.of(memberData.getFirstName(), memberData.getLastName()),
                    MemberBirthDate.of(memberData.getBirthDate()),
                    MemberKind.of(memberData.getKind())
            );
        } catch (PropertiesNotChangedException e) {
            ResponseEntity.status(HttpStatus.NOT_MODIFIED).build();
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping(value = "/members/{memberReference}")
    public ResponseEntity<Void> quitMember(@PathVariable String memberReference) {
        memberService.quitMember(
                MemberReference.of(UUID.fromString(memberReference))
        );
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
