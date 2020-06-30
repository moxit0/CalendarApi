package org.southgate.calendar.meeting.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.southgate.calendar.exception.DuplicateParticipationsFoundException;
import org.southgate.calendar.exception.IllegalMeetingAccessException;
import org.southgate.calendar.exception.MeetingOverlapException;
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
        String meetingRoomName = meetingToSave.getMeetingRoom().getId().getName();
        LocalDateTime start = meetingToSave.getStart();
        LocalDateTime end = meetingToSave.getEnd();
        long numberOverlappedMeetingsForRoom =
                meetingRepository.countOverlappedMeetingsForRoom(meetingRoomName, start, end);

        if (numberOverlappedMeetingsForRoom > 0) {
            throw new MeetingOverlapException(start, end);
        }

        Optional<Participant> foundParticipant = findConflictedParticipants(meetingToSave);
        if (foundParticipant.isPresent()) {
            throw new DuplicateParticipationsFoundException(foundParticipant.get().getName());
        }

        meetingToSave.setAssociatedUser(getCurrentUserName());
        return meetingRepository.save(meetingToSave);
    }

    public Iterable<Meeting> saveAll(Iterable<Meeting> meetings) {
        return StreamSupport.stream(meetings.spliterator(), false)
                .map(this::save)
                .collect(Collectors.toList());
    }

    public Meeting merge(Meeting meetingToUpdate) {
        Optional<Meeting> foundMeeting = meetingRepository.findById(meetingToUpdate.getId());
        return foundMeeting.map(m -> {
            if (notOwnMeeting(m.getAssociatedUser())) {
                throw new IllegalMeetingAccessException();
            }
            if (participantsChanged(meetingToUpdate, m)) {
                Optional<Participant> foundParticipant = findConflictedParticipants(meetingToUpdate);
                if (foundParticipant.isPresent()) {
                    throw new DuplicateParticipationsFoundException(foundParticipant.get().getName());
                }
                return internalUpdate(meetingToUpdate);
            }
            if (overlapsWithOtherMeetings(m, meetingToUpdate)) {
                return internalUpdate(meetingToUpdate);
            } else {
                return save(meetingToUpdate);
            }
        }).orElseGet(() -> save(meetingToUpdate));
    }

    public void delete(long id) {
        meetingRepository.deleteById(id);
    }

    private Optional<Participant> findConflictedParticipants(Meeting meetingToSave) {
        List<Meeting> overlappedMeetingsForDistinctRoom = meetingRepository.findOverlappedMeetingsForDistinctRoom(
                meetingToSave.getMeetingRoom().getId().getName(),
                meetingToSave.getStart(),
                meetingToSave.getEnd());
        Optional<Participant> foundParticipant = Optional.empty();
        for (Participant p : meetingToSave.getParticipants()) {
            foundParticipant = overlappedMeetingsForDistinctRoom.stream()
                    .flatMap(o -> o.getParticipants().stream())
                    .filter(pp -> Objects.equals(pp.getName(), p.getName()))
                    .findAny();
        }
        return foundParticipant;
    }

    private Meeting internalUpdate(Meeting meetingToUpdate) {
        meetingToUpdate.setAssociatedUser(getCurrentUserName());
        return meetingRepository.save(meetingToUpdate);
    }

    private boolean notOwnMeeting(String associatedUser){
        return !Objects.equals(associatedUser, getCurrentUserName());
    }

    private boolean participantsChanged(Meeting meetingToUpdate, Meeting m) {
        return !Objects.equals(m.getParticipants(), meetingToUpdate.getParticipants());
    }

    private boolean overlapsWithOtherMeetings(Meeting existingMeeting, Meeting meetingToUpdate) {
        return existingMeeting.getStart().isEqual(meetingToUpdate.getStart())
                && existingMeeting.getEnd().isEqual(meetingToUpdate.getEnd())
                && Objects.equals(existingMeeting.getMeetingRoom(), meetingToUpdate.getMeetingRoom());
    }

    private String getCurrentUserName() {
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}
