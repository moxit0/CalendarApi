package org.southgate.calendar.meeting.rest;

import java.security.Principal;
import java.util.Optional;

import org.southgate.calendar.meeting.entity.Meeting;
import org.southgate.calendar.meeting.service.MeetingsService;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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
    public Meeting createSingle(@RequestBody Meeting meeting) {
//        meeting.getParticipants().forEach(meeting::addParticipant);
        return meetingsService.save(meeting);
    }

}
