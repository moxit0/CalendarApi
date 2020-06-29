package org.southgate.calendar.rest;

import org.southgate.calendar.entity.Meeting;
import org.southgate.calendar.repository.MeetingRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeetingsController {

    private final MeetingRepository meetingRepository;

    public MeetingsController(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    @GetMapping({"/", "/meetings"})
    public Iterable<Meeting> index() {
        return meetingRepository.findAll();
    }

    @GetMapping("/meetings/{id}")
    public Meeting findById(@PathVariable Long id) {
        return meetingRepository.findById(id).orElse(new Meeting());
    }

    @PostMapping("/meetings")
    public Meeting createSingle(@RequestBody Meeting meeting) {
        return meetingRepository.save(meeting);
    }
}
