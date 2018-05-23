package be.jstack.ticketing.util.mail;

import be.jstack.ticketing.entity.Answer;
import be.jstack.ticketing.entity.Ticket;
import be.jstack.ticketing.entity.User;

public class HTMLMail {
    private final User user;
    private final Ticket ticket;
    private final Answer answer;

    public HTMLMail(User user, Ticket ticket, Answer answer) {
        this.user = user;
        this.ticket = ticket;
        this.answer = answer;
    }

    String getTo() {
        return this.ticket.getCreator().getEmail();
    }

    String getSubject() {
        return "Your ticket has been answered by" + user.getFirstName();
    }

    String getContent() {
        return "<html>" +
                "<body>" +
                "<p>Hello client,</p>" +
                "<p>This an <strong>HTML</strong> email content !</p>" +
                "<div><p>The answer suggested is:</p>" +
                answer.getAnswer() +
                "</div></body>" +
                "</html>";
    }
}
