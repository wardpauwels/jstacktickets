package be.jstack.ticketing.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

@Data
public class Answer {

    @DBRef
    @ApiModelProperty(notes = "The user who responded to a ticket", required = true)
    private User user;
    @ApiModelProperty(notes = "The answer provided by the resolving user", required = true)
    private String answer;
    @ApiModelProperty(notes = "The date and time when the answer was submitted")
    private Date answerTime;

    public Answer(User user, String answer) {
        this.user = user;
        this.answer = answer;
        this.answerTime = new Date();
    }
}