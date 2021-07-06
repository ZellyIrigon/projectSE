package pt.ulisboa.tecnico.learnjava.sibs.domain;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;

public class Operation {

	private final int value;
	private final String targetIban;
	private final String sourceIban;
	
	private State status;
	
	
	

	public Operation(String sourceIban, String targetIban, int value) throws OperationException {
		checkParameters(value);
		this.value = value;

		if (invalidString(targetIban)) {
			throw new OperationException();
		}
		if (invalidString(sourceIban)) {
			throw new OperationException();
		}

		this.targetIban = targetIban;
		this.sourceIban = sourceIban;
		
		//atribuir ao status registered
		status = new Registered();
		
	}

	private void checkParameters(int value) throws OperationException {

		if (value <= 0) {
			throw new OperationException(value);
		}
	}

	public int getValue() {
		return this.value;
	}

	private boolean invalidString(String name) {
		return name == null || name.length() == 0;
	}

	public int commission() {
		return (int) (getValue() * 0.02);
	}

	public String getTargetIban() {
		return this.targetIban;
	}
	public String getSourceIban() {
		return this.sourceIban;
	}
	
	//retornar o status atual da respetiva operacao
	public State getState() {
		return this.status;
	}
	
	public void setState(State status) {
		 this.status = status;
	}
	
	public void process(Services services) throws AccountException {
		
		getState().process(this, services);
		
	}
	
	public void cancel(Services services) throws AccountException {

		getState().cancel(this,services);

	}
}
