package pt.ulisboa.tecnico.learnjava.sibs.domain;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;

public abstract class State {
	
	public abstract void process(Operation operation, Services services) throws AccountException;
	
	public abstract void cancel(Operation operation, Services services) throws AccountException;

}
