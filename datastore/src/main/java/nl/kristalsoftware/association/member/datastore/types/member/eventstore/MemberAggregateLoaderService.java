package nl.kristalsoftware.association.member.datastore.types.member.eventstore;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.domain.member.Member;
import nl.kristalsoftware.association.member.domain.member.properties.MemberReference;
import nl.kristalsoftware.datastore.base.eventstore.BaseAggregateLoader;
import nl.kristalsoftware.domain.base.EventStore;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

import java.util.UUID;

@RequiredArgsConstructor
@Component
public class MemberAggregateLoaderService extends BaseAggregateLoader<Member> implements EventStore<Member,MemberReference> {

    @Override
    protected Member createAggregateRoot(UUID reference, ApplicationEventPublisher applicationEventPublisher) {
        return createMember(MemberReference.of(reference), applicationEventPublisher);
    }

    @Override
    public Member loadAggregate(ApplicationEventPublisher eventPublisher) {
        return createAggregateRoot(UUID.randomUUID(), eventPublisher);
    }

    @Override
    public Member loadAggregate(MemberReference memberReference, ApplicationEventPublisher eventPublisher) {
        return loadEvents(memberReference.getValue(), eventPublisher);
    }

    private Member createMember(MemberReference memberReference, ApplicationEventPublisher eventPublisher) {
        return Member.of(memberReference, eventPublisher);
    }
}
