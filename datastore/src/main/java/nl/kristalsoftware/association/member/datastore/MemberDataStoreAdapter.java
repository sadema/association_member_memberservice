package nl.kristalsoftware.association.member.datastore;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.datastore.types.member.eventstore.MemberEventReaderService;
import nl.kristalsoftware.association.member.domain.member.Member;
import nl.kristalsoftware.association.member.domain.member.MemberDataStore;
import nl.kristalsoftware.association.member.domain.member.MemberReference;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class MemberDataStoreAdapter implements MemberDataStore {

    private final MemberEventReaderService memberEventStore;

//    private final PlayerViewStore playerViewStore;

    @Override
    public Member loadAggregate(ApplicationEventPublisher eventPublisher) {
        return Member.of(MemberReference.of(UUID.randomUUID()), eventPublisher);
    }

    @Override
    public Member loadAggregate(MemberReference memberReference, ApplicationEventPublisher eventPublisher) {
        return memberEventStore.loadEvents(memberReference.getValue(), eventPublisher);
    }

}
