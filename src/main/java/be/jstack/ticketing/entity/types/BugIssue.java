package be.jstack.ticketing.entity.types;

import be.jstack.ticketing.entity.Environment;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
public class BugIssue {

    @Id
    private String id;
    private String title;
    private String priorityLevel;
    private String url;
    private String type;
    private Environment environment;
    private String simulateSteps;
    private String currentOutcome;
    private String expectedOutcome;
    private ArrayList<Image> printscreens;
    private String username;
    private String projectId;
    private Date creationDate;
}