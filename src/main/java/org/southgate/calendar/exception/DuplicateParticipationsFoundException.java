package org.southgate.calendar.exception;

public class DuplicateParticipationsFoundException extends RuntimeException {

    public DuplicateParticipationsFoundException(String duplicateParticipant){
        super("Participant with name: "+duplicateParticipant+" participate in another meetings during this period.");
    }
}
