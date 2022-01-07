package nl.kristalsoftware.association.member.domain.concerned_person;

import lombok.Getter;
import nl.kristalsoftware.association.member.domain.concerned_person.properties.PersonName;
import nl.kristalsoftware.association.member.domain.member.properties.MemberReference;
import nl.kristalsoftware.domain.base.Aggregate;
import nl.kristalsoftware.domain.base.BaseAggregateRoot;
import nl.kristalsoftware.domain.base.PersistenceHandlerPort;
import nl.kristalsoftware.domain.base.annotations.AggregateRoot;

@Getter
@AggregateRoot
public class ConcernedPerson extends BaseAggregateRoot<ConcernedPerson,MemberReference> implements Aggregate<MemberReference> {

    private PersonName memberName;

//    private List<PersonRole> personRoleList;

    private ConcernedPerson(MemberReference reference, PersistenceHandlerPort<ConcernedPerson> persistenceHandler) {
        super(reference, persistenceHandler);
    }

    public static ConcernedPerson of(MemberReference reference, PersistenceHandlerPort<ConcernedPerson> persistenceHandler) {
        return new ConcernedPerson(reference, persistenceHandler);
    }

}
