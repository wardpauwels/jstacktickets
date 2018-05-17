package be.jstack.ticketing.exception;

import javax.ejb.ObjectNotFoundException;

public class CompanyNotFoundException extends ObjectNotFoundException {
    public CompanyNotFoundException(String message) {
        super(message);
    }
}
