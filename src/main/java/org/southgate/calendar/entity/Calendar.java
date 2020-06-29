package org.southgate.calendar.entity;

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
