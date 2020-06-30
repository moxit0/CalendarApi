package org.southgate.calendar.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//@ResponseStatus(value = HttpStatus.CONFLICT)
public class MeetingOverlapException extends RuntimeException {

    public MeetingOverlapException(LocalDateTime start, LocalDateTime end){
        super(String.format("Meeting interval overlaps with another one for start time %s and end time: %s", start, end));
    }
}
