package be.jstack.ticketing.service;

import be.jstack.ticketing.data.TicketRepository;
import be.jstack.ticketing.entity.Ticket;
import be.jstack.ticketing.entity.User;
import be.jstack.ticketing.entity.tickttypes.BugIssue;
import be.jstack.ticketing.entity.tickttypes.Improvement;
import be.jstack.ticketing.entity.tickttypes.NewFeature;
import be.jstack.ticketing.util.constants.TicketTypes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final UserService userService;

    @Autowired
    public TicketService(
            TicketRepository ticketRepository,
            UserService userService) {
        this.ticketRepository = ticketRepository;
        this.userService = userService;
    }

    public List<Ticket> findAllTickets(String tickettype) {
        List<Ticket> tickets = ticketRepository.findAll();
        if (tickettype == null) return tickets;
        else if (tickettype.equalsIgnoreCase(TicketTypes.BUG.getValue())) {
            return getBugTicketsFromList(tickets);
        } else if (tickettype.equalsIgnoreCase(TicketTypes.CHANGE.getValue()) ||
                tickettype.equalsIgnoreCase(TicketTypes.IMPROVEMENT.getValue())) {
            return getImprovementTicketsFromList(tickets);
        } else if (tickettype.equalsIgnoreCase(TicketTypes.FEATURE.getValue())) {
            return getNewFeatureTicketsFromList(tickets);
        } else return null;
    }

    public Ticket createNewTicket(Ticket ticket) {
        ticket.setCreationDate(new Date());
        Optional<User> user = userService.getCurrentLoggedInUser();
        user.ifPresent(ticket::setUser);
        return ticketRepository.save(ticket);
    }

    private List<Ticket> getBugTicketsFromList(List<Ticket> tickets) {
        return tickets
                .stream()
                .filter(BugIssue.class::isInstance)
                .map(BugIssue.class::cast)
                .collect(Collectors.toList());
    }

    private List<Ticket> getImprovementTicketsFromList(List<Ticket> tickets) {
        return tickets
                .stream()
                .filter(Improvement.class::isInstance)
                .map(Improvement.class::cast)
                .collect(Collectors.toList());
    }

    private List<Ticket> getNewFeatureTicketsFromList(List<Ticket> tickets) {
        return tickets
                .stream()
                .filter(NewFeature.class::isInstance)
                .map(NewFeature.class::cast)
                .collect(Collectors.toList());
    }
}