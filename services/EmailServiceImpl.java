package tn.saturn.spring.services;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
public class EmailServiceImpl implements IEmailService{
		@Autowired
	    private JavaMailSender javaMailSender;
		
		public void sendEmailAmountReceived(String email,Double amount){
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(email);
			msg.setSubject("Notification from your balance on MycroA");
	        msg.setText("Hello your balance account was updated /n Your current balance amount is : "+amount);
	        javaMailSender.send(msg);
		}
		
		
		public void sendEmailRefuseCase(String email){
			SimpleMailMessage msg = new SimpleMailMessage();
			msg.setTo(email);
			msg.setSubject("Notification from your case on MycroA");
	        msg.setText("Hello/n Your Case have been refused !");
	        javaMailSender.send(msg);
		}
}
