package be.jstack.ticketing.entity.tickttypes;

import be.jstack.ticketing.entity.Ticket;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class NewFeature extends Ticket {

    @ApiModelProperty(notes = "The description of a new feature idea", required = true)
    private String description;

    public String getDescription() {
        return "Description of the new feature:\n" + description;
    }
}