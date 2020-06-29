package org.southgate.calendar.repository;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.southgate.calendar.entity.Meeting;
import org.southgate.calendar.entity.MeetingRoom;
import org.southgate.calendar.entity.Participant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class MeetingRepositoryTest {

    @Autowired
    private MeetingRepository meetingRepository;

    @Test
    public void shouldCreateMeeting() {
        Meeting m = new Meeting();
        m.setName("Begin");
        m.setStart(LocalDateTime.now());
        m.setEnd(LocalDateTime.now());
        MeetingRoom mr = new MeetingRoom("Kiten");
//        mr.setName("Kiten");
        m.setMeetingRoom(mr);
        Participant p = new Participant();
        p.setName("Pesho");
        m.addParticipant(p);
        Meeting persisted = meetingRepository.save(m);
        System.out.println("<<<<<<<<<<<-------------------------------------------------------------------------------------------------------");
        System.out.println("Persisted: " + persisted);
        System.out.println("<<<<<<<<<<<-------------------------------------------------------------------------------------------------------");

    }
}
