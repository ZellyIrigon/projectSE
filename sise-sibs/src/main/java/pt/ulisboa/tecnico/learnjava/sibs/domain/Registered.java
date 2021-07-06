package pt.ulisboa.tecnico.learnjava.sibs.domain;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;

public class Registered extends State{
	
	
	public void process(Operation operation, Services services) throws AccountException {
		
		if (services.getAccountByIban(operation.getSourceIban()).getBalance() >= operation.getValue()) {
			services.withdraw(operation.getSourceIban(), operation.getValue());
			operation.setState(new Withdrawn());
		}
		else {
			operation.setState(new Retry());
		}
		
	}

	public void cancel(Operation operation, Services services) {
		operation.setState(new Cancelled());
	}

}
