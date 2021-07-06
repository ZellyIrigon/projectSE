package pt.ulisboa.tecnico.learnjava.sibs.domain;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;

public class Withdrawn extends State {

	
	public void process(Operation operation, Services services) throws AccountException {

		services.deposit(operation.getTargetIban(), operation.getValue());
		operation.setState(new Deposited());

	}

	public void cancel(Operation operation, Services services) throws AccountException {

		services.deposit(operation.getSourceIban(), operation.getValue());
		operation.setState(new Cancelled());

	}
}
