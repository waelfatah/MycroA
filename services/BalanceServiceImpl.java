package tn.saturn.spring.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import tn.saturn.spring.entities.Balance;
import tn.saturn.spring.repositories.BalanceRepository;

@Service
public class BalanceServiceImpl implements IBalanceService{
	
	@Autowired
	BalanceRepository balanceRepository;
	
	
	@Override
	public Balance addBalance(Balance b){
			return balanceRepository.save(b);
	}
	
	
	public void addAmount(Double amount,Balance b){
		Double temp = b.getAmount();
		b.setAmount(amount+temp);
		balanceRepository.save(b);
	}
	
	public void discountAmount(Double amount,Balance b){
		Double temp = b.getAmount();
		b.setAmount(temp-amount);
		balanceRepository.save(b);
	}
	
	
	
}
