package org.southgate.calendar.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.CONFLICT)
public class DuplicateParticipationsFoundException extends RuntimeException {

    public DuplicateParticipationsFoundException(String duplicateParticipant){
        super("Participant with name: "+duplicateParticipant+" participate in another meetings during this period.");
    }
}
