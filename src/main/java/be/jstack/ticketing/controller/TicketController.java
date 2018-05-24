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
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/tickets")
@Api(value = "Ticket controller", description = "Retrieve info about saved tickets, make new tickets or adapt existing ones.")
public class TicketController {
    private final TicketService ticketService;
    private ObjectMapper mapper = new ObjectMapper();

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    @ApiOperation(value = "View a list of all the current tickets with a specific type", response = Ticket.class, responseContainer = "List")
    public Stream getAllTickets(@RequestParam(value = "tickettype", required = false) String tickettype) {
        return ticketService.findAllTicketsForType(tickettype).stream();
    }

    @GetMapping("/{ticketId")
    @ApiOperation(value = "View info about the ticket with given ID", response = Ticket.class)
    public Optional<Ticket> getTicketWithId(@PathVariable String ticketId) {
        return ticketService.findById(ticketId);
    }

    @GetMapping("/projects/{projectId}")
    @ApiOperation(value = "Retrieve info about all the tickets, submitted for the project with given ID", response = Ticket.class, responseContainer = "List")
    public Stream getTicketsForProjectWithId(@PathVariable String projectId) {
        try {
            return ticketService.getTicketsForProjectWithId(projectId).stream();
        } catch (ProjectNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping
    @ApiOperation(value = "Submit a new ticket", response = Ticket.class)
    public Ticket addNewTicket(@RequestBody String ticket, @RequestParam(value = "tickettype") String tickettype) throws IOException {
        switch (TicketType.valueOf(tickettype.toUpperCase())) {
            case BUG:
                return ticketService.add(mapper.readValue(ticket, BugIssue.class));
            case IMPROVEMENT:
            case CHANGE:
                return ticketService.add(mapper.readValue(ticket, Improvement.class));
            case FEATURE:
                return ticketService.add(mapper.readValue(ticket, NewFeature.class));
            default:
                return null;
        }
    }

    @PostMapping("/{ticketId}/answers")
    @ApiOperation(value = "Answer on a ticket with the given ID", response = Ticket.class)
    public Ticket answerTicketWithId(@PathVariable String ticketId, @RequestBody String answer) {
        JSONObject jsonBody = new JSONObject(answer);
        return ticketService.answerOnTicketWithId(ticketId, jsonBody.getString("answer"));
    }

    @PatchMapping("/{ticketId}/resolvers")
    @ApiOperation(value = "Assign a resolver with given name, to a ticket with the given ID", response = void.class)
    public void assignResolverToTicket(@PathVariable String ticketId, @RequestBody String username) {
        JSONObject jsonBody = new JSONObject(username);
        ticketService.assignResolverToTicket(ticketId, jsonBody.getString("username"));
    }

    @PatchMapping("/{ticketId}/status")
    @ApiOperation(value = "Update the status of the ticket with given ID to the given status", response = void.class)
    public void updateTicketStatus(@PathVariable("ticketId") String ticketId, @RequestBody String ticketStatus) {
        JSONObject jsonBody = new JSONObject(ticketStatus);
        ticketService.updateTicketStatus(ticketId, TicketStatus.valueOf(jsonBody.getString("ticketStatus").toUpperCase()));
    }
}