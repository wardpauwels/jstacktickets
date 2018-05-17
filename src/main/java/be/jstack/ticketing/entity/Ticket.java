package be.jstack.ticketing.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import javax.persistence.Id;
import java.awt.*;
import java.util.ArrayList;
import java.util.Date;

@Data
public class Ticket {
    @Id
    private String id;
    private String title;
    private String priorityLevel;
    private String url;
    private String type;
    private ArrayList<Image> printscreens;
    @DBRef
    private User user;
    @DBRef
    private Project project;
    private Date creationDate;
}
