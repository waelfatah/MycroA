package tn.saturn.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

@Service
public class MailClient {
 
    private JavaMailSender mailSender;
    @Autowired
    MailContentBuilder mailContentBuilder;
    @Autowired
    public MailClient(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
 
    public void prepareAndSend(String recipient, String message, String firstname, String lastname, String contractDescription, double InsuredPropertyValue, double ContractPremium) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("wael.fatah@mycroa.tn");
            messageHelper.setTo(recipient);
            messageHelper.setSubject("Welcome To MycroA");
            String content = mailContentBuilder.build(message,firstname,lastname,contractDescription,InsuredPropertyValue,ContractPremium);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
        }
    }
 
}
