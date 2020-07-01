package org.southgate.calendar.meetingroom.entity;

import java.util.Objects;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

@Entity
public class MeetingRoom {

    @EmbeddedId
    private MeetingRoomId id;

    public MeetingRoom() {
    }

    public MeetingRoom(String name) {
        this.id = new MeetingRoomId(name);
    }

    public MeetingRoomId getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MeetingRoom))
            return false;
        MeetingRoom that = (MeetingRoom) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
