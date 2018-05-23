package be.jstack.ticketing.controller;

import be.jstack.ticketing.entity.Ticket;
import be.jstack.ticketing.entity.tickttypes.BugIssue;
import be.jstack.ticketing.entity.tickttypes.Improvement;
import be.jstack.ticketing.entity.tickttypes.NewFeature;
import be.jstack.ticketing.exception.ProjectNotFoundException;
import be.jstack.ticketing.service.TicketService;
import be.jstack.ticketing.util.constants.TicketStatus;
import be.jstack.ticketing.util.constants.TicketType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;
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
        return ticketService.findAllTicketsForType(tickettype).stream();
    }

    @GetMapping("/{ticketId")
    public Optional<Ticket> getTicketWithId(@PathVariable String ticketId) {
        return ticketService.findTicketWithId(ticketId);
    }

    @GetMapping("/projects/{projectId}")
    public Stream getTicketsForProjectWithId(@PathVariable String projectId) {
        try {
            return ticketService.getTicketsForProjectWithId(projectId).stream();
        } catch (ProjectNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/{ticketId}/seen")
    public void markTicketAsSeen(@PathVariable("ticketId") String ticketId) {
        ticketService.updateTicketStatus(ticketId, TicketStatus.SEEN);
    }

    @PostMapping
    public Ticket addNewTicket(@RequestBody String ticket, @RequestParam(value = "tickettype") String tickettype) throws IOException {
        switch (TicketType.valueOf(tickettype.toUpperCase())) {
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

    @PostMapping("/{ticketId}/answers")
    public Ticket answerTicketWithId(@PathVariable String ticketId, @RequestBody String answer) {
        JSONObject jsonBody = new JSONObject(answer);
        return ticketService.answerOnTicketWithId(ticketId, jsonBody.getString("answer"));
    }

    @PatchMapping("/{ticketId}/resolvers")
    public void assignResolverToTicket(@PathVariable String ticketId, @RequestBody String username) {
        JSONObject jsonBody = new JSONObject(username);
        ticketService.assignResolverToTicket(ticketId, jsonBody.getString("username"));
    }

    @PatchMapping("/{ticketId}/solved")
    public void ticketSolved(@PathVariable String ticketId) {
        ticketService.updateTicketStatus(ticketId, TicketStatus.SOLVED);
    }
}