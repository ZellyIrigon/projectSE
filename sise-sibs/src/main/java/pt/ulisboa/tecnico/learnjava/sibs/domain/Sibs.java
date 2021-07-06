package pt.ulisboa.tecnico.learnjava.sibs.domain;

import pt.ulisboa.tecnico.learnjava.bank.exceptions.AccountException;
import pt.ulisboa.tecnico.learnjava.bank.services.Services;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.OperationException;
import pt.ulisboa.tecnico.learnjava.sibs.exceptions.SibsException;

public class Sibs {
	final Operation[] operations;
	Services services;
	private int numberOfOperations =0;
	private int totalValueOfOperations =0;

	public Sibs(int maxNumberOfOperations, Services services) {
		this.operations = new Operation[maxNumberOfOperations];
		this.services = services;
	}

	public void transfer(String sourceIban, String targetIban, int amount) throws AccountException, OperationException, SibsException {

		
		if (sourceIban == null) {
			throw new AccountException();
		}

		else if (sourceIban == "") {
			throw new AccountException();
		}

		else if (targetIban == null) {
			throw new AccountException();
		}

		else if (targetIban == "") {
			throw new AccountException();
		}

		else if (amount <= 0) {
			throw new AccountException();
		}

		else {
			int posicao = addOperation(sourceIban, targetIban, amount);

		}
				
	}

	public int addOperation(String sourceIban, String targetIban, int value)
			throws OperationException, SibsException {
		int position = -1;
		for (int i = 0; i < this.operations.length; i++) {
			if (this.operations[i] == null) {
				position = i;
				numberOfOperations++;
				totalValueOfOperations +=value;
				break;
			}
		}

		if (position == -1) {
			throw new SibsException();
		}

		Operation operation = new Operation(sourceIban, targetIban, value);

		this.operations[position] = operation;
		return position;
	}

	public void removeOperation(int position) throws SibsException {
		if (position < 0 || position > this.operations.length) {
			throw new SibsException();
		}
		totalValueOfOperations -= getOperation(position).getValue();
		this.operations[position] = null;
		numberOfOperations--;

	}

	public Operation getOperation(int position) throws SibsException {
		if (position < 0 || position > this.operations.length) {
			throw new SibsException();
		}
		return this.operations[position];
	}
	
	public void processOperations() throws AccountException {
		for(Operation op : operations) {
			
			if(op!=null) {
				op.process(this.services);
			}
		}
	}
	
	public void cancelOperation(int position) throws SibsException, AccountException {
		getOperation(position).cancel(services);
	}
	

	public int getNumberOfOperations() {
		return numberOfOperations;
	}

	public int getTotalValueOfOperations() {
		return totalValueOfOperations;

	}

}
