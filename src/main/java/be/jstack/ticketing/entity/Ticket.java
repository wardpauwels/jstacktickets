package be.jstack.ticketing.entity;

import be.jstack.ticketing.util.constants.TicketStatus;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.Id;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
public class Ticket {
    @Id
    private String id;
    private String title;
    private String priorityLevel;
    private String url;
    private String ticketType;
    private List<Image> printscreens;
    private TicketStatus ticketStatus;
    @DBRef
    private User creator;
    @DBRef
    private User resolver;
    @DBRef
    private Project project;
    private Date creationDate;
    private Date dueDate;
    private Date ticketSeenTime;
    private List<Answer> answers;

    public void addAnswerToTicket(Answer answer) {
        if (answers == null) answers = new ArrayList<>();
        answers.add(answer);
    }

    public Answer getLastAnswer() {
        return answers.get(answers.size() - 1);
    }
}
