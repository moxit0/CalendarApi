package org.southgate.calendar.rest;

import org.southgate.calendar.entity.Calendar;
import org.southgate.calendar.entity.Meeting;
import org.southgate.calendar.entity.MeetingRoom;
import org.southgate.calendar.repository.MeetingRepository;
import org.southgate.calendar.repository.MeetingRoomRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarController {

    private final MeetingRoomRepository meetingRoomRepository;
    private final MeetingRepository meetingRepository;

    public CalendarController(MeetingRoomRepository meetingRoomRepository, MeetingRepository meetingRepository) {
        this.meetingRoomRepository = meetingRoomRepository;
        this.meetingRepository = meetingRepository;
    }

    @PostMapping("/calendar")
    public Calendar create(@RequestBody Calendar calendar) {
        Iterable<MeetingRoom> persistedRooms = meetingRoomRepository.saveAll(calendar.getMeetingRooms());
        Iterable<Meeting> persistedMeetings = meetingRepository.saveAll(calendar.getMeetings());
        Calendar persistedCalendar = new Calendar();
        persistedCalendar.setMeetingRooms(persistedRooms);
        persistedCalendar.setMeetings(persistedMeetings);

        return persistedCalendar;
    }
}
