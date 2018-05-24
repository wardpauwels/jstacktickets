package be.jstack.ticketing.entity;

import be.jstack.ticketing.util.constants.TicketStatus;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@NoArgsConstructor
@Document(collection = "ticket")
public class Ticket {

    @Id
    @ApiModelProperty(notes = "The database generated ticket ID")
    private String id;
    @ApiModelProperty(notes = "The subject of the submitted ticket", required = true)
    private String title;
    @ApiModelProperty(notes = "The service level of the ticket", required = true)
    private String priorityLevel;
    @ApiModelProperty(notes = "The URL of where the problem is happening")
    private String url;
    @ApiModelProperty(notes = "The type of the submitted ticket", required = true)
    private String ticketType;
    @ApiModelProperty(notes = "Printscreens or wireframes of the problem / improvement")
    private List<Image> printscreens;
    @ApiModelProperty(notes = "The current status of the ticket", required = true)
    private TicketStatus ticketStatus;
    @DBRef
    @ApiModelProperty(notes = "The person who created the ticket", required = true)
    private User creator;
    @DBRef
    @ApiModelProperty(notes = "The user of the staff who is assigned to solve the ticket")
    private User resolver;
    @DBRef
    @ApiModelProperty(notes = "The project which the ticket is related to", required = true)
    private Project project;
    @ApiModelProperty(notes = "The date and time when the ticket was created")
    private Date creationDate;
    @ApiModelProperty(notes = "The date when the ticket has to be solved, according to the service level")
    private Date dueDate;
    @ApiModelProperty(notes = "The date and time when the ticket has been seen by the staff")
    private Date ticketSeenTime;
    @ApiModelProperty(notes = "Answers provided to solve the ticket")
    private List<Answer> answers;

    public void addAnswerToTicket(Answer answer) {
        if (answers == null) answers = new ArrayList<>();
        answers.add(answer);
    }

    public Answer getLastAnswer() {
        return answers.get(answers.size() - 1);
    }
}
