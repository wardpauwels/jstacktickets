package be.jstack.ticketing.util.constants;

public enum TicketTypes {
    BUG("bug"),
    IMPROVEMENT("improvement"),
    CHANGE("change"),
    FEATURE("feature");

    private final String text;

    TicketTypes(final String text) {
        this.text = text;
    }

    public String getValue() {
        return text;
    }
}