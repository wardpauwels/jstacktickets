package be.jstack.ticketing.entity.tickttypes;

import be.jstack.ticketing.entity.Ticket;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class Improvement extends Ticket {

    @ApiModelProperty(notes = "The current feature which needs a change/improvement", required = true)
    private String feature;
    @ApiModelProperty(notes = "What does the current feature do now", required = true)
    private String currentFeature;
    @ApiModelProperty(notes = "What can make the feature better", required = true)
    private String improvement;

    public String getDescription() {
        String description = "Feature:\n" + feature;
        description += "\nCurrent feature:\n" + currentFeature;
        description += "\nImprovement:\n" + improvement;

        return description;
    }
}