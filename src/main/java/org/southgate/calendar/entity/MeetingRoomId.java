package org.southgate.calendar.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Embeddable
public class MeetingRoomId implements Serializable {

//    @Id
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
    public String toString() {
        final StringBuilder sb = new StringBuilder("MeetingRoomId{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
