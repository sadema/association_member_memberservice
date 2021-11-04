package nl.kristalsoftware.association.member.datastore.types.member;

import lombok.RequiredArgsConstructor;
import nl.kristalsoftware.association.member.datastore.types.member.eventstore.MemberEventWriterService;
import nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_edited.MemberEditedEventEntity;
import nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_kind_changed.MemberKindChangedEventEntity;
import nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_quited.MemberQuitedEventEntity;
import nl.kristalsoftware.association.member.datastore.types.member.eventstore.event.member_signed_up.MemberSignedUpEventEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberPersistenceService {

    private final MemberEventWriterService memberEventWriterService;

    @Transactional
    public void save(MemberSignedUpEventEntity memberSignedUpEventEntity) {
        memberEventWriterService.save(memberSignedUpEventEntity);
    }

    @Transactional
    public void save(MemberEditedEventEntity memberEditedEventEntity) {
        memberEventWriterService.save(memberEditedEventEntity);
    }

    public void save(MemberKindChangedEventEntity memberKindChangedEventEntity) {
        memberEventWriterService.save(memberKindChangedEventEntity);
    }

    public void save(MemberQuitedEventEntity memberQuitedEventEntity) {
        memberEventWriterService.save(memberQuitedEventEntity);
    }
}
