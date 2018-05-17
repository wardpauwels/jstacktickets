package be.jstack.ticketing.controller;

import be.jstack.ticketing.entity.Ticket;
import be.jstack.ticketing.entity.tickttypes.BugIssue;
import be.jstack.ticketing.entity.tickttypes.Improvement;
import be.jstack.ticketing.entity.tickttypes.NewFeature;
import be.jstack.ticketing.service.TicketService;
import be.jstack.ticketing.util.constants.TicketTypes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.stream.Stream;

@RestController
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public Stream getAllTickets(@RequestParam(value = "tickettype", required = false) String tickettype) {
        return ticketService.findAllTickets(tickettype).stream();
    }

    @PostMapping
    public Ticket addNewTicket(@RequestBody String ticket, @RequestParam(value = "tickettype") String tickettype) throws IOException {
        switch (TicketTypes.valueOf(tickettype.toUpperCase())) {
            case BUG:
                return ticketService.createNewTicket(mapper.readValue(ticket, BugIssue.class));
            case IMPROVEMENT:
            case CHANGE:
                return ticketService.createNewTicket(mapper.readValue(ticket, Improvement.class));
            case FEATURE:
                return ticketService.createNewTicket(mapper.readValue(ticket, NewFeature.class));
            default:
                return null;
        }
    }
}