package be.jstack.ticketing.entity.tickttypes;

import be.jstack.ticketing.entity.Ticket;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import java.io.Serializable;

@EqualsAndHashCode(callSuper = false)
@Data
@Entity
@NoArgsConstructor
@Document(collection = "ticket")
public class NewFeature extends Ticket implements Serializable {

    private String description;

    public String getDescription() {
        return "Decription of new feature:\n" + description;
    }
}