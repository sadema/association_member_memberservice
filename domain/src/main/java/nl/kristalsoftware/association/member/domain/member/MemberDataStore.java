package nl.kristalsoftware.association.member.domain.member;

import org.springframework.context.ApplicationEventPublisher;

public interface MemberDataStore {

    Member loadAggregate(ApplicationEventPublisher eventPublisher);

    Member loadAggregate(MemberReference memberReference, ApplicationEventPublisher eventPublisher);

}
