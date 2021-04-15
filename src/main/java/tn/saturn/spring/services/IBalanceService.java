package tn.saturn.spring.services;

import tn.saturn.spring.entities.Balance;

public interface IBalanceService {
	public Balance addBalance(Balance b);
	public void addAmount(Double amount,Balance b);
	public void discountAmount(Double amount,Balance b);
	
	
}
