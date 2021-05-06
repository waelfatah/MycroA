package tn.saturn.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;


@Service
public class MailClient {
		 

    
	private JavaMailSender mailSender;
	
    @Autowired
    MailContentBuilder mailContentBuilder;
    
    @Autowired
    public MailClient(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }
 
    public void prepareAndSend(String recipient, String firstname, String lastname, String description) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("wael.fatah@mycroa.tn");
            messageHelper.setTo(recipient);
            messageHelper.setSubject("Welcome To MycroA--NEW RECLAMATION");
            String content = mailContentBuilder.build(firstname,lastname,description);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
        }
    }
    
    public void prepareAndSend1(String recipient, String message, String firstname, String lastname, String contractDescription, double InsuredPropertyValue, double ContractPremium) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("wael.fatah@mycroa.tn");
            messageHelper.setTo(recipient);
            messageHelper.setSubject("Thank you for trusting us !");
            String content = mailContentBuilder.build1(message,firstname,lastname,contractDescription,InsuredPropertyValue,ContractPremium);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
        }
    }
    
    public void prepareAndSend2(String recipient, String message, String firstname, String lastname, String contractDescription, double InsuredPropertyValue, double ContractPremium) {
        MimeMessagePreparator messagePreparator = mimeMessage -> {
            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setFrom("wael.fatah@mycroa.tn");
            messageHelper.setTo(recipient);
            messageHelper.setSubject("Welcome To MycroA");
            String content = mailContentBuilder.build2(message,firstname,lastname,contractDescription,InsuredPropertyValue,ContractPremium);
            messageHelper.setText(content, true);
        };
        try {
            mailSender.send(messagePreparator);
        } catch (MailException e) {
            // runtime exception; compiler will not force you to handle it
        }
    }
}
