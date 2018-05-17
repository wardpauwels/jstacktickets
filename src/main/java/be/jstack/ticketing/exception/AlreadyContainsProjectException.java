package be.jstack.ticketing.exception;

import javax.ejb.DuplicateKeyException;

public class AlreadyContainsProjectException extends DuplicateKeyException {
    public AlreadyContainsProjectException(String message) {
        super(message);
    }
}
