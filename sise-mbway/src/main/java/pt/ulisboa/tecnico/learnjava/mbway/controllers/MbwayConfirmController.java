package pt.ulisboa.tecnico.learnjava.mbway.controllers;

import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.mbway.domain.ClientMbway;
import pt.ulisboa.tecnico.learnjava.mbway.domain.Mbway;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.MbwayException;

public class MbwayConfirmController {
	private Mbway mbway;
	
	public MbwayConfirmController(Services services, Mbway mbway) {
		this.mbway = mbway;
	}
	
	public String confirmAccount(String phoneNumber, Integer code) throws MbwayException {
		ClientMbway client = this.mbway.getClient(phoneNumber);
		//existe associacao, o cliente nao confirmou o codigo e o codigo que esta a passar é igual ao que foi emitido
		if(client != null && !client.getIsConfirmed() && client.getConfCode() == code) {
			client.setIsConfirmed(true);
			return "MBWAY association confirmed successfully!";
		}
		else {
			System.out.println(client);
			throw new MbwayException("Wrong confirmation code. Try association again.");
		}
	}
}
