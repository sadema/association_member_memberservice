package nl.kristalsoftware.association.member.domain.member.event;


import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.member.CurrentMemberState;

@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class MemberBaseEvent {

    @Getter
    private final CurrentMemberState currentState;

}
