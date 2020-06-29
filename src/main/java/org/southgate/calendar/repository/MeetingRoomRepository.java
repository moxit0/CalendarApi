package org.southgate.calendar.repository;

import java.util.List;

import org.southgate.calendar.entity.Meeting;
import org.southgate.calendar.entity.MeetingRoom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

//@PreAuthorize("hasRole('USER')")
@RepositoryRestResource(collectionResourceRel = "meeting-rooms", path = "meeting-rooms")
public interface MeetingRoomRepository extends CrudRepository<MeetingRoom, Long> {

    //    @PreAuthorize("hasRole('ADMIN')")
//    @Secured("ADMIN")
    List<MeetingRoom> findByName(@Param("name") String name);
}
