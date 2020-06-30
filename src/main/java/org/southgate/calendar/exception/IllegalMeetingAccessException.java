package org.southgate.calendar.exception;

public class IllegalMeetingAccessException extends RuntimeException {

    public IllegalMeetingAccessException() {
        super("Illegal access to meeting owned by other user.");
    }

    public IllegalMeetingAccessException(String message) {
        super(message);
    }
}
