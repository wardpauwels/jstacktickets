package be.jstack.ticketing.exception;

import javax.ejb.DuplicateKeyException;

public class AlreadyContainsUserException extends DuplicateKeyException {
    public AlreadyContainsUserException(String message) {
        super(message);
    }
}
