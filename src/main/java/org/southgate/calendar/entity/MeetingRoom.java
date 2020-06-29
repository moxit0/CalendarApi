package org.southgate.calendar.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class MeetingRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    public MeetingRoom() {
    }

    public MeetingRoom(String name) {
        this.name = name;
    }

    @Column(unique = true)
    private String name;

//    @OneToMany(mappedBy = "meetingRoom", fetch = FetchType.EAGER)
//    private List<Meeting> meetings = new ArrayList<>();

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
