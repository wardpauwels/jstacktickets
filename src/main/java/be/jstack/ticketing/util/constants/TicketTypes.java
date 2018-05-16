package be.jstack.ticketing.util.constants;

public enum TicketTypes {
    BUG("bug"),
    IMPROVEMENT("improvement"),
    CHANGE("change"),
    NEWFEATURE("newfeature");

    private final String text;

    TicketTypes(final String text) {
        this.text = text;
    }

    public String getValue() {
        return text;
    }
}