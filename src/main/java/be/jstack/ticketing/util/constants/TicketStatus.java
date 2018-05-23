package be.jstack.ticketing.util.constants;

public enum TicketStatus {
    NEW("NEW"),
    OPEN("OPEN"),
    SOLVED("SOLVED"),
    ANSWERED("ANSWERED"),
    SEEN("SEEN"),
    ASSIGNEDRESOLVER("ASSIGNEDRESOLVER");

    private String status;

    TicketStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}