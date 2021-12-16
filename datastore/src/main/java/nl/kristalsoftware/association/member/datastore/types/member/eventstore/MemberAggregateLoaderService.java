package nl.kristalsoftware.association.member.datastore.types.member.eventstore;

import nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.MemberEventFinder;
import nl.kristalsoftware.association.member.domain.member.Member;
import nl.kristalsoftware.association.member.domain.member.properties.MemberReference;
import nl.kristalsoftware.datastore.base.eventstore.BaseAggregateLoader;
import nl.kristalsoftware.datastore.base.eventstore.event.entity.UUIDBaseEventEntity;
import nl.kristalsoftware.domain.base.EventStore;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class MemberAggregateLoaderService extends BaseAggregateLoader<Member, UUIDBaseEventEntity> implements EventStore<Member,MemberReference> {

    public MemberAggregateLoaderService(MemberEventFinder memberEventFinder) {
        super(memberEventFinder);
    }

    @Override
    public Member loadAggregate(MemberReference memberReference, ApplicationEventPublisher eventPublisher) {
        Member member = Member.of(memberReference, eventPublisher);
        loadEvents(member);
        return member;
    }

}
