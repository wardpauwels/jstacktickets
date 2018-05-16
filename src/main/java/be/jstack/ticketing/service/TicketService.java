package be.jstack.ticketing.service;

import be.jstack.ticketing.data.BugIssueRepository;
import be.jstack.ticketing.data.ImprovementRepository;
import be.jstack.ticketing.data.NewFeatureRepository;
import be.jstack.ticketing.data.TicketRepository;
import be.jstack.ticketing.entity.User;
import be.jstack.ticketing.entity.types.BugIssue;
import be.jstack.ticketing.entity.types.Improvement;
import be.jstack.ticketing.entity.types.NewFeature;
import be.jstack.ticketing.entity.types.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final BugIssueRepository bugIssueRepository;
    private final ImprovementRepository improvementRepository;
    private final NewFeatureRepository newFeatureRepository;
    private final UserService userService;

    @Autowired
    public TicketService(
            TicketRepository ticketRepository, BugIssueRepository bugIssueRepository,
            ImprovementRepository improvementRepository,
            NewFeatureRepository newFeatureRepository,
            UserService userService) {
        this.ticketRepository = ticketRepository;
        this.bugIssueRepository = bugIssueRepository;
        this.improvementRepository = improvementRepository;
        this.newFeatureRepository = newFeatureRepository;
        this.userService = userService;
    }

    public List findAllTickets() {
        return ticketRepository.findAll();
    }

    public void createNewTicket(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    public void createNewBugIssue(BugIssue bugIssue) {
        bugIssue.setCreationDate(new Date());
        Optional<User> user = userService.findUserByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        user.ifPresent(bugIssue::setUser);
        bugIssueRepository.save(bugIssue);
    }

    public void createNewImprovement(Improvement improvement) {
        improvementRepository.save(improvement);
    }

    public void createNewNewFeature(NewFeature newFeature) {
        newFeatureRepository.save(newFeature);
    }
}