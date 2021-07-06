package pt.ulisboa.tecnico.learnjava.sibs.domain;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;

public class Deposited extends State {

	

	public void process(Operation operation, Services services) throws AccountException {

		if (services.getAccountByIban(operation.getSourceIban()).getBalance() > operation.commission()) {
			services.withdraw(operation.getSourceIban(), operation.commission());
			operation.setState(new Completed());
		} else {
			operation.setState(new Retry());
		}

	}

	public void cancel(Operation operation, Services services) throws AccountException {

		services.withdraw(operation.getTargetIban(), operation.getValue());
		services.deposit(operation.getSourceIban(), operation.getValue());
		operation.setState(new Cancelled());

	}

}
