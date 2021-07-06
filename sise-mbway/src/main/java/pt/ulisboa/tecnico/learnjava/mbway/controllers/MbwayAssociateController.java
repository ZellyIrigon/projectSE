package pt.ulisboa.tecnico.learnjava.mbway.controllers;

import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.mbway.domain.ClientMbway;
import pt.ulisboa.tecnico.learnjava.mbway.domain.Mbway;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.MbwayException;

public class MbwayAssociateController {

	private Mbway mbway;
	private Services services;
	
	public MbwayAssociateController(Services services, Mbway mbway) {
		this.services = services;
		this.mbway = mbway;
//		code++;
//		checkAccount(iban,phoneNumber);
//		this.clientMbway = new 
//		clientMbway.setIban(iban);
//		clientMbway.setPhoneNumber(phoneNumber);
	}
	
	public String associateAccount(String iban, String phoneNumber) throws MbwayException {
		ClientMbway client = this.mbway.getClient(phoneNumber);
		
		//caso 1 - nao existe associacao
		//caso 2 - existe associacao mas o cliente nao confirmou o codigo
		if(client == null || !client.getIsConfirmed()) {
			if(services.getAccountByIban(iban) != null) {
				int code = generateCode();
				client = new ClientMbway(iban, phoneNumber, code);
				this.mbway.addClient(phoneNumber, client);
				return "Code: " + code + " (don't share it with anyone)";
			}
		}
		throw new MbwayException("Error in account association");
	}
	
	public int generateCode() {
		return (int) (Math.random()*(100 + 1));
	}
	
	public Mbway getMbway() {
		return mbway;
	}
}



