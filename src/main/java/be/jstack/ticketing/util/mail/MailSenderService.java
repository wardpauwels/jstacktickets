package be.jstack.ticketing.util.mail;

import be.jstack.ticketing.entity.Answer;
import be.jstack.ticketing.entity.Ticket;
import be.jstack.ticketing.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailSenderService {

    private final JavaMailSender mailSender;
    private final MailContentBuilder mailContentBuilder;

    @Autowired
    public MailSenderService(JavaMailSender mailSender, MailContentBuilder mailContentBuilder) {
        this.mailSender = mailSender;
        this.mailContentBuilder = mailContentBuilder;
    }

    // Use it to send HTML emails
    public void sendMail(User user, Ticket ticket, Answer answer) {

        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
            helper.setTo(ticket.getCreator().getEmail());
            helper.setSubject(ticket.getTitle());
            String content = mailContentBuilder.build(user, ticket, answer);
            helper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            e.printStackTrace();
        }
    }
}