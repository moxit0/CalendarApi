package org.southgate.calendar.meeting.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.southgate.calendar.meeting.entity.Meeting;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MeetingRepository extends CrudRepository<Meeting, Long> {

    List<Meeting> findAllByAssociatedUser(@Param("associatedUser") String associatedUser);

    Optional<Meeting> findAllByIdAndAssociatedUser(@Param("id") Long id, @Param("associatedUser") String associatedUser);


    @Query("select count(m) from Meeting m where m.meetingRoom.id.name = :name "
            + "and ((m.start >= :start and m.start < :end) or (m.end > :start and m.end <= :end))")
    Long countOverlappedMeetingsForRoom(
            @Param("name") String name,
            @Param("start") LocalDateTime start,
            @Param("end")  LocalDateTime end);

    @Query("select m from Meeting m where m.meetingRoom.id.name <> :name "
            + "and ((m.start >= :start and m.start < :end) or (m.end > :start and m.end <= :end))")
    List<Meeting> findOverlappedMeetingsForDistinctRoom(
            @Param("name") String name,
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end);
}
