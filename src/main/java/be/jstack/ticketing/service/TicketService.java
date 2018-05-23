package be.jstack.ticketing.service;

import be.jstack.ticketing.data.TicketRepository;
import be.jstack.ticketing.entity.Answer;
import be.jstack.ticketing.entity.Project;
import be.jstack.ticketing.entity.Ticket;
import be.jstack.ticketing.entity.User;
import be.jstack.ticketing.entity.tickttypes.BugIssue;
import be.jstack.ticketing.entity.tickttypes.Improvement;
import be.jstack.ticketing.entity.tickttypes.NewFeature;
import be.jstack.ticketing.exception.ProjectNotFoundException;
import be.jstack.ticketing.util.constants.TicketStatus;
import be.jstack.ticketing.util.constants.TicketType;
import be.jstack.ticketing.util.mail.MailSenderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TicketService {
    private final TicketRepository ticketRepository;
    private final ProjectService projectService;
    private final UserService userService;
    private final MailSenderService mailSenderService;

    @Autowired
    public TicketService(
            TicketRepository ticketRepository,
            ProjectService projectService, UserService userService, MailSenderService mailSenderService) {
        this.ticketRepository = ticketRepository;
        this.projectService = projectService;
        this.userService = userService;
        this.mailSenderService = mailSenderService;
    }

    public List<Ticket> findAllTicketsForType(String tickettype) {
        List<Ticket> tickets = ticketRepository.findAll();
        if (tickettype == null) return tickets;
        else if (tickettype.equalsIgnoreCase(TicketType.BUG.getValue())) {
            return getBugTicketsFromList(tickets);
        } else if (tickettype.equalsIgnoreCase(TicketType.CHANGE.getValue()) ||
                tickettype.equalsIgnoreCase(TicketType.IMPROVEMENT.getValue())) {
            return getImprovementTicketsFromList(tickets);
        } else if (tickettype.equalsIgnoreCase(TicketType.FEATURE.getValue())) {
            return getNewFeatureTicketsFromList(tickets);
        } else return null;
    }

    public Optional<Ticket> findTicketWithId(String ticketId) {
        return ticketRepository.findById(ticketId);
    }

    public Ticket createNewTicket(Ticket ticket) {
        Optional<User> user = userService.getCurrentLoggedInUser();
        user.ifPresent(ticket::setCreator);
        ticket.setCreationDate(new Date());
        ticket.setTicketStatus(TicketStatus.NEW);
        ticket = ticketRepository.save(ticket);
        sendMail(ticket, null);
        return ticket;
    }

    public Ticket updateTicketStatus(String ticketId, TicketStatus ticketStatus) {
        Optional<Ticket> ticket = ticketRepository.findById(ticketId);
        if (ticket.isPresent()) {
            ticket.get().setTicketStatus(ticketStatus);
            if (ticketStatus == TicketStatus.SEEN) ticket.get().setTicketSeenTime(new Date());
            return ticketRepository.save(ticket.get());
        }
        return null;
    }

    public List<Ticket> getTicketsForProjectWithId(String projectId) throws ProjectNotFoundException {
        Optional<Project> optionalProject = projectService.findProjectById(projectId);
        if (optionalProject.isPresent()) {
            return ticketRepository.findAllByProject(optionalProject.get());
        } else throw new ProjectNotFoundException(projectId);
    }

    public void assignResolverToTicket(String ticketId, String username) {
        Optional<Ticket> ticket = findTicketWithId(ticketId);
        Optional<User> user = userService.findUserByUsername(username);
        if (ticket.isPresent() && user.isPresent()) {
            ticket.get().setResolver(user.get());
            ticket.get().setTicketStatus(TicketStatus.ASSIGNEDRESOLVER);
            sendMail(ticket.get(), null);
            ticketRepository.save(ticket.get());
        }
    }

    public Ticket answerOnTicketWithId(String ticketId, String answerString) {
        Optional<Ticket> ticket = findTicketWithId(ticketId);
        Optional<User> user = userService.getCurrentLoggedInUser();
        if (user.isPresent()) {
            if (ticket.isPresent()) {
                if (user.get().isStaff() || ticket.get().getCreator().equals(user.get())) {
                    Answer answer = createAnswer(user.get(), answerString);
                    ticket.get().addAnswerToTicket(answer);
                    ticket = Optional.of(updateTicketStatus(ticket.get().getId(), TicketStatus.ANSWERED));
                    sendMail(ticket.get(), answer);
                    return ticket.get();
                }
            }
        }
        return null;
    }

    private Answer createAnswer(User user, String answer) {
        return new Answer(user, answer);
    }

    private void sendMail(Ticket ticket, Answer answer) {
        Optional<User> user = userService.getCurrentLoggedInUser();
        Runnable myrunnable = () -> user.ifPresent(user1 -> mailSenderService.sendMail(user1, ticket, answer));
        new Thread(myrunnable).start();//Call it when you need to run the function
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