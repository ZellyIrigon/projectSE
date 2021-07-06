package pt.ulisboa.tecnico.learnjava.mbway.controllers;

import pt.ulisboa.tecnico.learnjava.bank.domain.Account;
import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.mbway.domain.ClientMbway;
import pt.ulisboa.tecnico.learnjava.mbway.domain.Mbway;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.MbwayException;

public class MbwayTransferController {

	private Mbway mbway;
	private Services services;
	
	public MbwayTransferController(Services services, Mbway mbway) {
		this.services = services;
		this.mbway = mbway;
//		code++;
//		checkAccount(iban,phoneNumber);
//		this.clientMbway = new 
//		clientMbway.setIban(iban);
//		clientMbway.setPhoneNumber(phoneNumber);
	}
	
	public String transferMoney(String sourceNumber, String targetNumber, Integer amount) throws MbwayException {
		ClientMbway sourceClient = this.mbway.getClient(sourceNumber);
		ClientMbway targetClient = this.mbway.getClient(targetNumber);
		//caso 1 - nao existe cliente remetente
		//caso 2 - existe cliente remetente mas nao existe cliente destino
		if(sourceClient == null || targetClient == null)
			throw new MbwayException("Wrong phone number.");
				//caso 1 - cliente remetente nao confirmou o codigo
				//caso 2 - cliente remetente confirmou o codigo mas cliente destino nao confirmou codigo
		else if(!sourceClient.getIsConfirmed() || !targetClient.getIsConfirmed())
			throw new MbwayException("Accounts not confirmed.");
		else {
			Account sourceAccount = services.getAccountByIban(sourceClient.getIban());
			Account targetAccount = services.getAccountByIban(targetClient.getIban());
			if(sourceAccount.getBalance() < amount)
				throw new MbwayException("Not enough money on the source account.");
			else {
				try {
					sourceAccount.withdraw(amount);
					targetAccount.deposit(amount);
				} catch (AccountException e) {
					
				}
				return "Transfer performed successfully!";
			}
		}
	}
	
//	conta 1 -> n1 / iban 1
//	
//	conta 2 -> n1 / iban 1
//	
//	conta 3 -> n1 / iban 2
//	conta 4 -> n2 / iban 1
	
	
//	private void checkAccount(String iban, String phoneNumber) {
//		for (String i: mbway.)
//			if i != iban ou do phoneNumber
//				mbway.addClient(ClienteMbway(iban,phoneNumber));
//			else
//				utilizador ja existe
//	}
	
	
//	private void checkCode(String code) throws BankException {
//		// code size is three
//		if (code == null || code.length() != 3) {
//			throw new MbwayException();
//		}
//
//		// banks have a unique code
//		if (getBankByCode(code) != null) {
//			throw new BankException();
//		}
//
//	}
}
