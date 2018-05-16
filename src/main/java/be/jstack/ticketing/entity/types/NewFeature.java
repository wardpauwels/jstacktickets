package be.jstack.ticketing.entity.types;

import be.jstack.ticketing.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.awt.*;
import java.util.ArrayList;

@Data
@Entity
@NoArgsConstructor
public class NewFeature {

    @Id
    private String id;
    private String title;
    private String priorityLevel;
    private String url;
    private String type;

    private String description;

    private ArrayList<Image> printscreens;
    private String username;
}