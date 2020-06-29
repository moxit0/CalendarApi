package org.southgate.calendar.repository;

import java.util.List;

import org.southgate.calendar.entity.Meeting;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
//import org.springframework.security.access.annotation.Secured;

//@PreAuthorize("hasRole('USER')")
@RepositoryRestResource(collectionResourceRel = "meetings", path = "meetings")
public interface MeetingRepository extends CrudRepository<Meeting, Long> {

    //    @PreAuthorize("hasRole('ADMIN')")
//    @Secured("ADMIN")
    List<Meeting> findByName(@Param("name") String name);
}
