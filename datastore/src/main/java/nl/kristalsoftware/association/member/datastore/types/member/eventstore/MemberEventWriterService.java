package nl.kristalsoftware.association.member.datastore.types.member.eventstore;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_edited.MemberEditedEventEntity;
import nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_signed_up.MemberSignedUpEventEntity;
import nl.kristalsoftware.datastore.base.eventstore.EventStoreRepository;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberEventWriterService {

    private final EventStoreRepository eventStoreRepository;

//    @Override
//    public <V extends BaseEventEntity> void saveEventEntity(V eventEntity) {
//        eventStoreRepository.save(eventEntity);
//    }

    public void save(MemberSignedUpEventEntity memberSignedUpEventEntity) {
        eventStoreRepository.save(memberSignedUpEventEntity);
//        this.saveEventEntity(memberSignedUpEventEntity);
    }

    public void save(MemberEditedEventEntity memberEditedEventEntity) {
        eventStoreRepository.save(memberEditedEventEntity);
//        this.saveEventEntity(memberEditedEventEntity);
    }

}
