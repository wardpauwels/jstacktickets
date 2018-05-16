package be.jstack.ticketing.controller;

import be.jstack.ticketing.entity.types.BugIssue;
import be.jstack.ticketing.entity.types.Improvement;
import be.jstack.ticketing.entity.types.NewFeature;
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
    public Stream getAllTickets() {
        return ticketService.findAllTickets().stream();
    }

    @PostMapping
    public void addNewTicket(@RequestBody String ticket, @RequestParam(value = "tickettype") String tickettype) throws IOException {
        switch (TicketTypes.valueOf(tickettype.toUpperCase())) {
            case BUG:
                ticketService.createNewTicket(mapper.readValue(ticket, BugIssue.class));
                break;
            case IMPROVEMENT:
            case CHANGE:
                ticketService.createNewTicket(mapper.readValue(ticket, Improvement.class));
                break;
            case NEWFEATURE:
                ticketService.createNewTicket(mapper.readValue(ticket, NewFeature.class));
        }
    }
}