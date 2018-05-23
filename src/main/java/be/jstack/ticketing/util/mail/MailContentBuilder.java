package be.jstack.ticketing.util.mail;

import be.jstack.ticketing.entity.Answer;
import be.jstack.ticketing.entity.Ticket;
import be.jstack.ticketing.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailContentBuilder {

    private TemplateEngine templateEngine;

    @Autowired
    public MailContentBuilder(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    <T extends Ticket> String build(User user, T ticket, Answer answer) {
        Context context = new Context();
        context.setVariable("user", user);
        context.setVariable("ticket", ticket);
        context.setVariable("answer", answer);
        return templateEngine.process("mailTemplate", context);
    }
}