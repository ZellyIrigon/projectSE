package pt.ulisboa.tecnico.learnjava.mbway.controllers;

import pt.ulisboa.tecnico.learnjava.bank.domain.Account;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.mbway.domain.ClientMbway;
import pt.ulisboa.tecnico.learnjava.mbway.domain.Mbway;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.MbwayException;

public class MbwaySplitInsuranceController {

	private Mbway mbway;
	private Services services;
	
	public MbwaySplitInsuranceController(Services services, Mbway mbway) {
		this.services = services;
		this.mbway = mbway;
	}
	
	public String splitInsurance(Integer nFriends, Integer totalAmount, String sourceNumber) {
		
		while (nFriends > 0) {
			int amount = this.transferMoney(this.mbway.getClient(sourceNumber), null, totalAmount);
			totalAmount -= amount;
			nFriends--;
		}
		return "Insurance paid successfully!";
	}
	
	public void transferMoney(String sourceNumber, String targetNumber, Integer amount) throws MbwayException {
		ClientMbway sourceClient = this.mbway.getClient(sourceNumber);
		ClientMbway targetClient = this.mbway.getClient(targetNumber);
		//caso 1 - nao existe cliente remetente
		//caso 2 - existe cliente remetente mas nao existe cliente destino
		if(sourceClient == null || targetClient == null)
			throw new MbwayException("Wrong phone number.");
				//caso 1 - cliente remetente nao confirmou o codigo
				//caso 2 - cliente remetente confirmou o codigo mas cliente destino nao confirmou codigo
		else if(!sourceClient.getIsConfirmed() || !targetClient.getIsConfirmed())
			throw new MbwayException("Friend "+ sourceClient.getPhoneNumber() + " is not registered.");
		else {
			Account sourceAccount = services.getAccountByIban(sourceClient.getIban());
			Account targetAccount = services.getAccountByIban(targetClient.getIban());
			if(sourceAccount.getBalance() < amount)
				throw new MbwayException("Oh no! One friend doesn't have money to pay!");
			else {
				try {
					sourceAccount.withdraw(amount);
					targetAccount.deposit(amount);
				} catch (AccountException e) {
					
				}
				
			}
		}
	}

}
