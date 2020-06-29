package org.southgate.calendar.entity;

import org.southgate.calendar.meeting.entity.Meeting;
import org.southgate.calendar.meetingroom.entity.MeetingRoom;

public class Calendar {

    private Iterable<MeetingRoom> meetingRooms;
    private Iterable<Meeting> meetings;

    public Iterable<MeetingRoom> getMeetingRooms() {
        return meetingRooms;
    }

    public void setMeetingRooms(Iterable<MeetingRoom> meetingRooms) {
        this.meetingRooms = meetingRooms;
    }

    public Iterable<Meeting> getMeetings() {
        return meetings;
    }

    public void setMeetings(Iterable<Meeting> meetings) {
        this.meetings = meetings;
    }
}
