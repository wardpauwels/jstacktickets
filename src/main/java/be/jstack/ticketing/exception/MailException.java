package be.jstack.ticketing.exception;

public class MailException extends Exception {
    public MailException() {
        super("Mail couldn't be sent");
    }
}
