package be.jstack.ticketing.entity.tickttypes;

import be.jstack.ticketing.entity.Environment;
import be.jstack.ticketing.entity.Ticket;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@NoArgsConstructor
@Document(collection = "ticket")
public class BugIssue extends Ticket {

    private Environment environment;
    private String simulateSteps;
    private String currentOutcome;
    private String expectedOutcome;
}