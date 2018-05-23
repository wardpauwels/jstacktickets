package be.jstack.ticketing.entity;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.Date;

@Data
public class Answer {

    @DBRef
    private User user;
    private String answer;
    private Date answerTime;

    public Answer(User user, String answer) {
        this.user = user;
        this.answer = answer;
        this.answerTime = new Date();
    }
}