package be.jstack.ticketing.entity.tickttypes;

import be.jstack.ticketing.entity.Environment;
import be.jstack.ticketing.entity.Ticket;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Data
public class BugIssue extends Ticket {

    @ApiModelProperty(notes = "The environment where the bug is happening")
    private Environment environment;
    @ApiModelProperty(notes = "A detailed description of how to replicate the bug", required = true)
    private String simulateSteps;
    @ApiModelProperty(notes = "The current outcome of the steps", required = true)
    private String currentOutcome;
    @ApiModelProperty(notes = "The expected outcome of the steps", required = true)
    private String expectedOutcome;

    public String getDescription() {
        String description = "Steps simulated for bug:\n" + simulateSteps;
        description += "\nCurrent outcome:\n" + currentOutcome;
        description += "\nExpected outcome:\n" + expectedOutcome;

        return description;
    }

}