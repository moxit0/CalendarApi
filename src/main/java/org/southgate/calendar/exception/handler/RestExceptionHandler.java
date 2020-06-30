package org.southgate.calendar.exception.handler;

import org.southgate.calendar.exception.DuplicateParticipationsFoundException;
import org.southgate.calendar.exception.IllegalMeetingAccessException;
import org.southgate.calendar.exception.MeetingOverlapException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseBody
    @ExceptionHandler(value = {
            MeetingOverlapException.class,
            DuplicateParticipationsFoundException.class,
            IllegalMeetingAccessException.class
    })
    @ResponseStatus(HttpStatus.CONFLICT)
    String meetingOverlapHandler(RuntimeException ex) {
        return ex.getMessage();
    }
}
