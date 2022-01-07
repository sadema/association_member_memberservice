package nl.kristalsoftware.association.member.domain.member.event.member_kind_changed;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.member.CurrentMemberState;

@RequiredArgsConstructor(staticName = "of")
public class MemberKindChangedMessage {

    private final MemberKindChanged memberKindChanged;

    private final CurrentMemberState stateBeforeEvent;

}
