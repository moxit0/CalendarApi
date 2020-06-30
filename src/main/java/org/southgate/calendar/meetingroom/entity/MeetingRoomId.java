package org.southgate.calendar.meetingroom.entity;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

@Embeddable
public class MeetingRoomId implements Serializable {

    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "meeting_room_id")
    private long id;

    @Column(name = "meeting_room_name")
    private String name;

    public MeetingRoomId() {
    }

    public MeetingRoomId(String name) {
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof MeetingRoomId))
            return false;
        MeetingRoomId that = (MeetingRoomId) o;
        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MeetingRoomId{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
