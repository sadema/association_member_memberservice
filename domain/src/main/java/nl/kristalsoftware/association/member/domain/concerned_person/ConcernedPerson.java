package nl.kristalsoftware.association.member.domain.concerned_person;

import lombok.Getter;
import nl.kristalsoftware.association.member.domain.concerned_person.event.ConcernedPersonEvent;
import nl.kristalsoftware.association.member.domain.concerned_person.properties.PersonName;
import nl.kristalsoftware.association.member.domain.member.properties.MemberReference;
import nl.kristalsoftware.domain.base.Aggregate;
import nl.kristalsoftware.domain.base.BaseAggregateRoot;
import nl.kristalsoftware.domain.base.annotations.AggregateRoot;
import org.springframework.context.ApplicationEventPublisher;

@Getter
@AggregateRoot
public class ConcernedPerson extends BaseAggregateRoot<MemberReference, ConcernedPersonEvent> implements Aggregate<MemberReference> {

    private PersonName memberName;

//    private List<PersonRole> personRoleList;

    private ConcernedPerson(MemberReference reference, ApplicationEventPublisher eventPublisher) {
        super(reference, eventPublisher);
    }

    public static ConcernedPerson of(MemberReference reference, ApplicationEventPublisher eventPublisher) {
        return new ConcernedPerson(reference, eventPublisher);
    }

}
