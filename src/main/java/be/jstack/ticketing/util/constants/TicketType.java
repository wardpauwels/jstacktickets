package be.jstack.ticketing.util.constants;

public enum TicketType {
    BUG("bug"),
    IMPROVEMENT("improvement"),
    CHANGE("change"),
    FEATURE("feature");

    private final String text;

    TicketType(final String text) {
        this.text = text;
    }

    public String getValue() {
        return text;
    }
}