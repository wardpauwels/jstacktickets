package be.jstack.ticketing.entity.types;

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
public class Improvement extends Ticket implements Serializable {

    private String improvementExplanation;
    private String currentFeature;
    private String improvement;
}