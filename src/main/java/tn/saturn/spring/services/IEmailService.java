package tn.saturn.spring.services;

public interface IEmailService {
	public void sendEmailAmountReceived(String email,Double amount);
	public void sendEmailRefuseCase(String email);
}
