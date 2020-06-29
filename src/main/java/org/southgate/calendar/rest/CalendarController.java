package org.southgate.calendar.rest;

import org.southgate.calendar.entity.Calendar;
import org.southgate.calendar.meeting.entity.Meeting;
import org.southgate.calendar.meeting.service.MeetingsService;
import org.southgate.calendar.meetingroom.entity.MeetingRoom;
import org.southgate.calendar.meetingroom.repository.MeetingRoomRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CalendarController {

    private final MeetingRoomRepository meetingRoomRepository;
    private final MeetingsService meetingsService;

    public CalendarController(MeetingRoomRepository meetingRoomRepository, MeetingsService meetingsService) {
        this.meetingRoomRepository = meetingRoomRepository;
        this.meetingsService = meetingsService;
    }

    @PostMapping("/calendar")
    public Calendar create(@RequestBody Calendar calendar) {
        Iterable<MeetingRoom> persistedRooms = meetingRoomRepository.saveAll(calendar.getMeetingRooms());
        Iterable<Meeting> persistedMeetings = meetingsService.saveAll(calendar.getMeetings());
        Calendar persistedCalendar = new Calendar();
        persistedCalendar.setMeetingRooms(persistedRooms);
        persistedCalendar.setMeetings(persistedMeetings);

        return persistedCalendar;
    }
}
