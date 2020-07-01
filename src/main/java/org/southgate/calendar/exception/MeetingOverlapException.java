package org.southgate.calendar.exception;

import java.time.LocalDateTime;

public class MeetingOverlapException extends RuntimeException {

    public MeetingOverlapException(LocalDateTime start, LocalDateTime end){
        super(String.format("Meeting interval overlaps with another one for start time %s and end time: %s", start, end));
    }
}
