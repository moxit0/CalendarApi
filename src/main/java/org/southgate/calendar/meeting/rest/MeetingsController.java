package org.southgate.calendar.meeting.rest;

import org.southgate.calendar.meeting.entity.Meeting;
import org.southgate.calendar.meeting.service.MeetingsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MeetingsController {

    private final MeetingsService meetingsService;

    public MeetingsController(MeetingsService meetingsService) {
        this.meetingsService = meetingsService;
    }

    @GetMapping({"/", "/meetings"})
    public Iterable<Meeting> index() {
        return meetingsService.findAll();
    }

    @GetMapping("/meetings/{id}")
    public Meeting findById(@PathVariable Long id) {
        return meetingsService.findById(id).orElse(new Meeting());
    }

    @PostMapping("/meetings")
    public Meeting create(@RequestBody Meeting meeting) {
        return meetingsService.save(meeting);
    }

    @PutMapping("/meetings")
    public Meeting update(@RequestBody Meeting meeting) {
        return meetingsService.merge(meeting);
    }

    @DeleteMapping("/meetings/{id}")
    public void delete(@PathVariable Long id) {
        meetingsService.delete(id);
    }
}
