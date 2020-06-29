package org.southgate.calendar.meeting.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.southgate.calendar.meeting.entity.Meeting;
import org.southgate.calendar.meeting.entity.Participant;
import org.southgate.calendar.meeting.repository.MeetingRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class MeetingsService {

    private final MeetingRepository meetingRepository;

    public MeetingsService(MeetingRepository meetingRepository) {
        this.meetingRepository = meetingRepository;
    }

    public Iterable<Meeting> findAll() {
        return meetingRepository.findAllByAssociatedUser(getCurrentUserName());
    }

    public Optional<Meeting> findById(Long id) {
        return meetingRepository.findAllByIdAndAssociatedUser(id, getCurrentUserName());
    }

    public Meeting save(Meeting meetingToSave) {
        long numberOverlappedMeetingsForRoom = meetingRepository.countOverlappedMeetingsForRoom(
                meetingToSave.getMeetingRoom().getId().getName(),
                meetingToSave.getStart(), meetingToSave.getEnd());

        List<Meeting> overlappedMeetingsForDistinctRoom = meetingRepository
                .findOverlappedMeetingsForDistinctRoom(
                        meetingToSave.getMeetingRoom().getId().getName(),
                        meetingToSave.getStart(), meetingToSave.getEnd());
        boolean foundParticipant = false;
        for (Participant p : meetingToSave.getParticipants()) {
            foundParticipant = overlappedMeetingsForDistinctRoom.stream()
                    .flatMap(o -> o.getParticipants().stream())
                    .anyMatch(pp -> pp.getName().equalsIgnoreCase(p.getName()));
        }
        if (numberOverlappedMeetingsForRoom > 0){
            System.out.println("Failed pair: "+ String.join(" | ", meetingToSave.getStart().toString(), meetingToSave.getEnd().toString()));
            throw new RuntimeException("numberOverlappedMeetingsForRoom: " + numberOverlappedMeetingsForRoom);
        }

        if (foundParticipant)
            throw new RuntimeException("foundParticipant: " + foundParticipant);

        meetingToSave.setAssociatedUser(getCurrentUserName());
        System.out.println("Succeeded pair: "+ String.join(" | ", meetingToSave.getStart().toString(), meetingToSave.getEnd().toString()));
        return meetingRepository.save(meetingToSave);
    }


    public Iterable<Meeting> saveAll(Iterable<Meeting> meetings) {
        return StreamSupport.stream(meetings.spliterator(), false)
                .map(this::save)
                .collect(Collectors.toList());
    }

    private String getCurrentUserName(){
//        Optional<String> username = Optional.empty();
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        if (!(authentication instanceof AnonymousAuthenticationToken)) {
//            username = Optional.ofNullable(authentication.getName());
//        }
//        return username;
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
