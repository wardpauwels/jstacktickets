package be.jstack.ticketing.exception;

import javax.ejb.ObjectNotFoundException;

public class ProjectNotFoundException extends ObjectNotFoundException {

    public ProjectNotFoundException(String message) {
        super(message);
    }
}